package com.ndz.app.service;

import com.ndz.app.dto.BaseSearch;
import com.ndz.app.dto.RoleDto;
import com.ndz.app.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
    author: NMDuc
    created_at: 2/21/2024
    github: https://github.com/NDZwei
*/
public interface RoleService {
    List<RoleDto> getAll();

    RoleDto getById(Long id);

    RoleDto getByName(String name);

    RoleDto save(RoleDto dto);

    Boolean delete(Long id);

    Page<RoleDto> getPage(BaseSearch dto);
}
