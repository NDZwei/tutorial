package com.ndz.app.service.impl;

import com.ndz.app.dto.AdministrativeUnitDto;
import com.ndz.app.entity.AdministrativeUnit;
import com.ndz.app.repository.AdministrativeUnitRepository;
import com.ndz.app.service.SyncDataService;
import com.ndz.app.utils.SyncDataUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
    author: NMDuc
    created_at: 3/16/2024
    github: https://github.com/NDZwei
*/
@Transactional
@Service
public class SyncDataServiceImpl implements SyncDataService {
    private static final Logger logger = LoggerFactory.getLogger(SyncDataServiceImpl.class);

    @Resource
    private AdministrativeUnitRepository administrativeUnitRepository;

    @Override
    public void syncAdministrativeById(Long id) {
        AdministrativeUnit administrativeUnit = administrativeUnitRepository.findById(id).orElse(null);
        if (administrativeUnit != null) {
            logger.info("=====Start sync {} at: {}", administrativeUnit.getName(), LocalDateTime.now() + "=====");
            List<AdministrativeUnitDto> administrativeUnits = new ArrayList<>();
            administrativeUnits.add(new AdministrativeUnitDto(
                    administrativeUnit.getId(),
                    administrativeUnit.getCode(),
                    administrativeUnit.getName(),
                    administrativeUnit.getLevel(),
                    administrativeUnit.getParentId()
            ));
            SyncDataUtils.sendAdministrative(administrativeUnits);
        }
    }

    @Override
    public void syncAllAdministrative() {
        List<AdministrativeUnitDto> allProvinces = administrativeUnitRepository.getAllByProvince();
        if(!CollectionUtils.isEmpty(allProvinces)) {
            logger.info("=====Start sync province at: {}", LocalDateTime.now() + "=====");
            SyncDataUtils.sendAdministrative(allProvinces);
            if(!CollectionUtils.isEmpty(allProvinces)) {
                for(AdministrativeUnitDto province : allProvinces) {
                    List<AdministrativeUnitDto> allDistricts = administrativeUnitRepository.getAllDistrictByProvince(province.getId());
                    logger.info("=====Start sync district from province: {}", province.getName() + "=====");
                    SyncDataUtils.sendAdministrative(allDistricts);
                    if(!CollectionUtils.isEmpty(allDistricts)) {
                        for(AdministrativeUnitDto district : allDistricts) {
                            List<AdministrativeUnitDto> allCommunes = administrativeUnitRepository.getAllCommuneByDistrict(district.getId());
                            logger.info("=====Start sync commune from district: {}", district.getName());
                            SyncDataUtils.sendAdministrative(allCommunes);
                        }
                    }
                }
            }
        }

    }
}
