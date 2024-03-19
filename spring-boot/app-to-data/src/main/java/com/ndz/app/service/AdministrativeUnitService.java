package com.ndz.app.service;

import com.ndz.app.dto.AdministrativeUnitDto;
import com.ndz.app.dto.BaseSearch;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/*
    author: NMDuc
    created_at: 3/6/2024
    github: https://github.com/NDZwei
*/
public interface AdministrativeUnitService {
    AdministrativeUnitDto save(AdministrativeUnitDto dto);

    void saveList(List<AdministrativeUnitDto> dtos);
}
