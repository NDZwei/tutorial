package com.ndz.app.controller;

import com.ndz.app.dto.AdministrativeUnitDto;
import com.ndz.app.dto.BaseResponse;
import com.ndz.app.service.AdministrativeUnitService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
    author: NMDuc
    created_at: 3/9/2024
    github: https://github.com/NDZwei
*/
@RestController
@RequestMapping(value = "/administrative-unit")
public class AdministrativeUnitController extends BaseController {
    @Resource
    private AdministrativeUnitService service;

    @GetMapping(value = "/all-province")
    public BaseResponse getAllProvince() {
        try {
            List<AdministrativeUnitDto> data = service.getAllProvince();
            return getResponse200(data);
        } catch (Exception e) {
            return getResponse500(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public BaseResponse getById(@PathVariable("id") Long id) {
        try {
            AdministrativeUnitDto data = service.getById(id);
            return getResponse200(data);
        } catch (Exception e) {
            return getResponse500(e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public BaseResponse save(@RequestBody AdministrativeUnitDto dto) {
        try {
            AdministrativeUnitDto data = service.save(dto);
            return getResponse200(data);
        } catch (Exception e) {
            return getResponse500(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public Boolean delete(@PathVariable("id") Long id) {
        try {
            return service.delete(id);
        } catch (Exception e) {
            return false;
        }
    }

    @GetMapping(value = "/download-excel-data")
    public void downloadExcelData(HttpServletResponse response) throws IOException {
        Workbook wb = service.downloadExcelData();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=administrative-unit.xlsx");
        response.flushBuffer();
        wb.write(response.getOutputStream());
    }

    @PostMapping(value = "/import-excel-data")
    public void importExcelData(@RequestParam("file_data") MultipartFile fileData) {
        service.importDataFromExcel(fileData);
    }
}
