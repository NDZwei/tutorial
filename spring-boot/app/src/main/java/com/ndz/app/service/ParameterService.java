package com.ndz.app.service;

import com.ndz.app.dto.BaseSearch;
import com.ndz.app.dto.ParameterDto;
import org.springframework.data.domain.Page;

import java.util.List;

/*
    author: NMDuc
    created_at: 3/16/2024
    github: https://github.com/NDZwei
*/
public interface ParameterService {
    List<ParameterDto> getAllByType(String type);

    ParameterDto getById(Long id);

    ParameterDto save(ParameterDto dto);

    boolean delete(Long id);

    Page<ParameterDto> getPage(BaseSearch dto);
}
