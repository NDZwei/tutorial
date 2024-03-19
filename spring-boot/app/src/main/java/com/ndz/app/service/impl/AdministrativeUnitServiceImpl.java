package com.ndz.app.service.impl;

import com.ndz.app.dto.AdministrativeUnitDto;
import com.ndz.app.dto.BaseSearch;
import com.ndz.app.dto.convert.ExcelToAdministrativeUnit;
import com.ndz.app.entity.AdministrativeUnit;
import com.ndz.app.repository.AdministrativeUnitRepository;
import com.ndz.app.service.AdministrativeUnitService;
import com.ndz.app.utils.EnumClass;
import com.ndz.app.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/*
    author: NMDuc
    created_at: 3/6/2024
    github: https://github.com/NDZwei
*/
@Service
@Transactional
public class AdministrativeUnitServiceImpl implements AdministrativeUnitService {
    private static final Logger logger = LoggerFactory.getLogger(AdministrativeUnitServiceImpl.class);
    @Resource
    private AdministrativeUnitRepository repository;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public AdministrativeUnitDto getById(Long id) {
        AdministrativeUnit administrativeUnit = repository.findById(id).orElse(null);
        return administrativeUnit != null ? new AdministrativeUnitDto(administrativeUnit) : null;
    }

    @Override
    public AdministrativeUnitDto save(AdministrativeUnitDto dto) {
        if(dto == null) {
            return null;
        }
        AdministrativeUnit administrativeUnit = null;
        if(dto.getId() != null) {
            administrativeUnit = repository.findById(dto.getId()).orElse(null);
        }
        if(administrativeUnit == null) {
            administrativeUnit = new AdministrativeUnit();
        }
        administrativeUnit.setCode(dto.getCode());
        administrativeUnit.setName(dto.getName());
        administrativeUnit.setLevel(dto.getLevel());
        AdministrativeUnit parent = null;
        if(dto.getParent() != null && dto.getParent().getId() != null) {
            parent = repository.findById(dto.getParent().getId()).orElse(null);
        }
        administrativeUnit.setParent(parent);
        administrativeUnit = repository.save(administrativeUnit);
        return new AdministrativeUnitDto(administrativeUnit);
    }

    @Override
    public List<AdministrativeUnitDto> getAllProvince() {
        return repository.getAllProvince();
    }

    @Override
    public List<AdministrativeUnitDto> getAllByParent(Long parentId) {
        List<AdministrativeUnit> administrativeUnits = repository.getAllByParentId(parentId);
        return !CollectionUtils.isEmpty(administrativeUnits) ?
                administrativeUnits.stream().map(AdministrativeUnitDto::new).collect(Collectors.toList())
                : null;
    }

    @Override
    public Boolean delete(Long id) {
        AdministrativeUnit administrativeUnit = repository.findById(id).orElse(null);
        if (administrativeUnit != null) {
            if(!CollectionUtils.isEmpty(administrativeUnit.getChildren())) {
                administrativeUnit.getChildren().forEach(child -> {
                    if(!CollectionUtils.isEmpty(child.getChildren())) {
                        child.getChildren().forEach(item -> {
                            item.setVoided(true);
                            repository.save(item);
                        });
                    }
                    child.setVoided(true);
                    repository.save(child);
                });
            }
            administrativeUnit.setVoided(true);
            repository.save(administrativeUnit);
        }
        return null;
    }

    @Override
    public Page<AdministrativeUnitDto> getPage(BaseSearch dto) {
        int pageIndex = dto.getPageIndex() != null ? dto.getPageIndex() : 1;
        int pageSize = dto.getPageSize() != null ? dto.getPageSize() : 10;

        if (pageIndex > 0) {
            pageIndex--;
        } else {
            pageIndex = 0;
        }
        String hqlSelect = "select new com.ndz.app.dto.AdministrativeUnitDto(e) from AdministrativeUnit e ";
        String hqlCount = "select count(e.id) from AdministrativeUnit e ";
        String whereClause = " where (1=1) ";
        String orderBy = " order by e.updatedAt desc";
        if (StringUtils.hasText(dto.getTextSearch())) {
            whereClause += " and e.code like :textSearch or e.name like :textSearch";
        }
        if(dto.getParentId() != null) {
            whereClause += " and e.parentId = :parentId";
        } else {
            whereClause += " and e.parentId is null";
        }
        hqlSelect += whereClause + orderBy;
        hqlCount += whereClause;
        Query q = manager.createQuery(hqlSelect, AdministrativeUnitDto.class);
        Query qCount = manager.createQuery(hqlCount);
        if (StringUtils.hasText(dto.getTextSearch())) {
            q.setParameter("textSearch", '%' + dto.getTextSearch() + '%');
            qCount.setParameter("textSearch", '%' + dto.getTextSearch() + '%');
        }
        if(dto.getParentId() != null) {
            q.setParameter("parentId", dto.getParentId());
            qCount.setParameter("parentId", dto.getParentId());
        }
        int startPosition = pageIndex * pageSize;
        q.setFirstResult(startPosition);
        q.setMaxResults(pageSize);
        List<AdministrativeUnitDto> results = q.getResultList();
        long count = (long) qCount.getSingleResult();
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return new PageImpl<>(results, pageable, count);
    }

    @Override
    public Workbook downloadExcelData() {
        try {
            InputStream inputStream = new ClassPathResource("templates/excel/administrative-unit.xlsx").getInputStream();
            return new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void importDataFromExcel(MultipartFile multipartFile) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(multipartFile.getBytes());
            Workbook workbook = new XSSFWorkbook(bis);
            List<ExcelToAdministrativeUnit> administrativeUnits = ExcelUtils.readDataFromExcel(workbook);
            if(!CollectionUtils.isEmpty(administrativeUnits)) {
                AdministrativeUnit province = null;
                AdministrativeUnit district = null;
                AdministrativeUnit commune = null;
                Date startTime = new Date();
                for(ExcelToAdministrativeUnit dto : administrativeUnits) {
                    // province
                    if(dto.getProvinceCode() != null) {
                        province = repository.getByCode(dto.getProvinceCode());
                        if(province == null) {
                            province = new AdministrativeUnit();
                            province.setCode(dto.getProvinceCode());
                            province.setName(dto.getProvinceName());
                            province.setLevel(EnumClass.ADMINISTRATIVE_UNIT_LEVEL.PROVINCE.getLevel());
                            province = repository.save(province);
                        }
                    }
                    // district
                    if(dto.getDistrictCode() != null) {
                        district = repository.getByCode(dto.getDistrictCode());
                        if(district == null) {
                            district = new AdministrativeUnit();
                            district.setCode(dto.getDistrictCode());
                            district.setName(dto.getDistrictName());
                            district.setParent(province);
                            district.setLevel(EnumClass.ADMINISTRATIVE_UNIT_LEVEL.DISTRICT.getLevel());
                            district = repository.save(district);
                        }
                    }
                    // commune
                    if(dto.getCommuneCode() != null) {
                        commune = repository.getByCode(dto.getCommuneCode());
                        if(commune == null) {
                            commune = new AdministrativeUnit();
                            commune.setCode(dto.getCommuneCode());
                            commune.setName(dto.getCommuneName());
                            commune.setParent(district);
                            commune.setLevel(EnumClass.ADMINISTRATIVE_UNIT_LEVEL.COMMUNE.getLevel());
                            repository.save(commune);
                        }
                    }
                }
                Date endTime = new Date();
                logger.info("Request import excel administrative uit from {} to {} and duration {}", startTime, endTime, (endTime.getTime() - startTime.getTime()));
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
