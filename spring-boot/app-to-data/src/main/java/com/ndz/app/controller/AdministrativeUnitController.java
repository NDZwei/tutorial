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

    @PostMapping(value = "/save-list")
    public BaseResponse saveList(@RequestBody List<AdministrativeUnitDto> dtos) {
        try {
            service.saveList(dtos);
            return getResponse200("Success");
        } catch (Exception e) {
            return getResponse500(e.getMessage());
        }
    }
}
