package com.ndz.app.controller;

import com.ndz.app.dto.AdministrativeUnitDto;
import com.ndz.app.dto.BaseResponse;
import com.ndz.app.service.AdministrativeUnitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/*
    author: NMDuc
    created_at: 3/6/2024
    github: https://github.com/NDZwei
*/
@RestController
@RequestMapping(value = "/api/public")
public class PublicController extends BaseController {
    @Resource
    private AdministrativeUnitService administrativeUnitService;

    @GetMapping(value = "/administrative-unit/all")
    public BaseResponse getAll() {
        try {
            List<AdministrativeUnitDto> data = administrativeUnitService.getAll();
            return getResponse200(data);
        } catch (Exception e) {
            return getResponse500(e.getMessage());
        }
    }
}
