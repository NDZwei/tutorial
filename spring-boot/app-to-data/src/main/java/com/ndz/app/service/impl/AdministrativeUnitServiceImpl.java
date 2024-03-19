package com.ndz.app.service.impl;

import com.ndz.app.dto.AdministrativeUnitDto;
import com.ndz.app.dto.BaseSearch;
import com.ndz.app.entity.AdministrativeUnit;
import com.ndz.app.repository.AdministrativeUnitRepository;
import com.ndz.app.service.AdministrativeUnitService;
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

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.io.InputStream;
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
    @Resource
    private AdministrativeUnitRepository repository;

    @Override
    public AdministrativeUnitDto save(AdministrativeUnitDto dto) {
        if(dto == null) {
            return null;
        }
        AdministrativeUnit administrativeUnit = null;
        if(dto.getId() != null) {
            administrativeUnit = repository.findById(dto.getId()).orElse(null);
        }
        if(administrativeUnit == null && dto.getCode() != null) {
            administrativeUnit = repository.getByCode(dto.getCode());
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
    public void saveList(List<AdministrativeUnitDto> dtos) {
        if(!CollectionUtils.isEmpty(dtos)) {
            for(AdministrativeUnitDto dto : dtos) {
                this.save(dto);
            }
        }
    }
}
