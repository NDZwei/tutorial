package com.ndz.app.service.impl;

import com.ndz.app.dto.BaseSearch;
import com.ndz.app.dto.RoleDto;
import com.ndz.app.entity.Role;
import com.ndz.app.repository.RoleRepository;
import com.ndz.app.service.RoleService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
    author: NMDuc
    created_at: 2/22/2024
    github: https://github.com/NDZwei
*/
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleRepository repository;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<RoleDto> getAll() {
        List<Role> roles = repository.findAll();
        return !CollectionUtils.isEmpty(roles) ?
                roles.stream().map(RoleDto::new).collect(Collectors.toList()) : new ArrayList<>();
    }

    @Override
    public RoleDto getById(Long id) {
        Role role = repository.findById(id).orElse(null);
        return role != null ? new RoleDto(role) : null;
    }

    @Override
    public RoleDto getByName(String name) {
        Role role = repository.getRoleByName(name);
        return role != null ? new RoleDto(role) : null;
    }

    @Override
    public RoleDto save(RoleDto dto) {
        if(dto == null) {
            return null;
        }
        Role role = null;
        if(dto.getId() != null) {
            role = repository.findById(dto.getId()).orElse(null);
        }
        if(dto.getName() != null) {
            role = repository.getRoleByName(dto.getName());
        }
        if(role == null) {
            role = new Role();
        }
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());
        role = repository.save(role);
        return new RoleDto(role);
    }

    @Override
    public Boolean delete(Long id) {
        if(id != null) {
            Role role = repository.findById(id).orElse(null);
            if(role != null) {
                repository.deleteById(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public Page<RoleDto> getPage(BaseSearch dto) {
        int pageIndex = dto.getPageIndex() != null ? dto.getPageIndex() -1 : 0;
        int pageSize = dto.getPageSize() != null ? dto.getPageSize() : 10;
        String hqlSelect = "select new com.ndz.app.dto.RoleDto(e) from Role e ";
        String hqlCount = "select count(e.id) from Role e ";
        String whereClause = " where (1=1) ";
        String orderBy = " order by e.updatedAt desc";
        if (StringUtils.hasText(dto.getTextSearch())) {
            whereClause += " e.name like :textSearch or e.description like :textSearch";
        }
        hqlSelect += whereClause + orderBy;
        hqlCount += whereClause;
        Query q = manager.createQuery(hqlSelect, RoleDto.class);
        Query qCount = manager.createQuery(hqlCount);
        if (StringUtils.hasText(dto.getTextSearch())) {
            q.setParameter("textSearch", '%' + dto.getTextSearch() + '%');
            qCount.setParameter("textSearch", '%' + dto.getTextSearch() + '%');
        }
        int startPosition = pageIndex * pageSize;
        q.setFirstResult(startPosition);
        q.setMaxResults(pageSize);
        List<RoleDto> results = q.getResultList();
        long count = (long) qCount.getSingleResult();
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return new PageImpl<>(results, pageable, count);
    }
}
