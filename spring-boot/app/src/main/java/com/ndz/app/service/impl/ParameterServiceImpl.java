package com.ndz.app.service.impl;

import com.ndz.app.dto.BaseSearch;
import com.ndz.app.dto.NotificationDto;
import com.ndz.app.dto.ParameterDto;
import com.ndz.app.entity.Parameter;
import com.ndz.app.repository.ParameterRepository;
import com.ndz.app.service.ParameterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/*
    author: NMDuc
    created_at: 3/16/2024
    github: https://github.com/NDZwei
*/
@Transactional
@Service
public class ParameterServiceImpl implements ParameterService {
    @Resource
    private ParameterRepository repository;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<ParameterDto> getAllByType(String type) {
        return repository.getAllByType(type);
    }

    @Override
    public ParameterDto getById(Long id) {
        Parameter parameter = repository.findById(id).orElse(null);
        return parameter != null ? new ParameterDto(parameter) : null;
    }

    @Override
    public ParameterDto save(ParameterDto dto) {
        if(dto == null) {
            return null;
        }
        Parameter parameter = null;
        if(dto.getId() != null) {
            parameter = repository.findById(dto.getId()).orElse(null);
        }
        if(dto.getCode() != null) {
            parameter = repository.getAllByCode(dto.getCode());
        }
        if(parameter == null) {
            parameter = new Parameter();
        }
        parameter.setCode(dto.getCode());
        parameter.setName(dto.getName());
        parameter.setType(dto.getType());
        parameter = repository.save(parameter);
        return new ParameterDto(parameter);
    }

    @Override
    public boolean delete(Long id) {
        if(id != null) {
            Parameter parameter = repository.findById(id).orElse(null);
            if(parameter != null) {
                repository.delete(parameter);
                return true;
            }
        }
        return false;
    }

    @Override
    public Page<ParameterDto> getPage(BaseSearch dto) {
        int pageIndex = dto.getPageIndex() != null ? dto.getPageIndex() -1 : 0;
        int pageSize = dto.getPageSize() != null ? dto.getPageSize() : 10;
        String hql = "select new com.ndz.app.dto.ParameterDto(e) from Parameter e ";
        String hqlCount = "select count(e.id) from Parameter e ";
        String whereClause = "where (e.voided is false or e.voided is null) ";
        String orderBy = " order by e.updatedAt desc";
        if(StringUtils.hasText(dto.getTextSearch())) {
            whereClause += " and e.code like :textSearch or e.name like :textSearch";
        }
        if(StringUtils.hasText(dto.getParameterType())) {
            whereClause += " and e.type like :parameterType";
        }
        hql += whereClause + orderBy;
        hqlCount += whereClause;
        Query querySelect = manager.createQuery(hql, ParameterDto.class);
        Query queryCount = manager.createQuery(hqlCount);
        if(StringUtils.hasText(dto.getTextSearch())) {
            querySelect.setParameter("textSearch", '%' + dto.getTextSearch() + '%');
            queryCount.setParameter("textSearch", '%' + dto.getTextSearch() + '%');
        }
        if(StringUtils.hasText(dto.getParameterType())) {
            querySelect.setParameter("parameterType", dto.getParameterType());
            queryCount.setParameter("parameterType", dto.getParameterType());
        }
        int startPosition = pageIndex * pageSize;
        querySelect.setFirstResult(startPosition);
        querySelect.setMaxResults(pageSize);
        List<ParameterDto> results = querySelect.getResultList();
        long count = (long) queryCount.getSingleResult();
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return new PageImpl<>(results, pageable, count);
    }
}
