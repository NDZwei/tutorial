package com.ndz.app.service.impl;

import com.ndz.app.dto.AdministrativeUnitDto;
import com.ndz.app.dto.RoleDto;
import com.ndz.app.entity.AdministrativeUnit;
import com.ndz.app.entity.Role;
import com.ndz.app.repository.AdministrativeUnitRepository;
import com.ndz.app.service.AdministrativeUnitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public List<AdministrativeUnitDto> getAll() {
        List<AdministrativeUnit> administrativeUnits = repository.findAll();
        return !CollectionUtils.isEmpty(administrativeUnits) ?
                administrativeUnits.stream().map(AdministrativeUnitDto::new).collect(Collectors.toList())
                : new ArrayList<>();
    }
}
