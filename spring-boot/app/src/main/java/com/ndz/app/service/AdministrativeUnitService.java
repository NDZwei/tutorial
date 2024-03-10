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
    AdministrativeUnitDto getById(Long id);

    AdministrativeUnitDto save(AdministrativeUnitDto dto);

    List<AdministrativeUnitDto> getAllProvince();

    List<AdministrativeUnitDto> getAllByParent(Long parentId);

    Boolean delete(Long id);

    Page<AdministrativeUnitDto> getPage(BaseSearch dto);

    Workbook downloadExcelData();

    void importDataFromExcel(MultipartFile multipartFile);
}
