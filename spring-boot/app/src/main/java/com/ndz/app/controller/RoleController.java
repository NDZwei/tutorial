package com.ndz.app.controller;

import com.ndz.app.dto.BaseResponse;
import com.ndz.app.dto.BaseSearch;
import com.ndz.app.dto.RoleDto;
import com.ndz.app.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/*
    author: NMDuc
    created_at: 2/23/2024
    github: https://github.com/NDZwei
*/
@RestController
@RequestMapping(value = "/role")
public class RoleController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Resource
    private RoleService service;

    @GetMapping(value = "/all")
    public BaseResponse getAll() {
        List<RoleDto> data = service.getAll();
        return getResponse200(data);
    }

    @GetMapping(value = "/{id}")
    public BaseResponse getById(@PathVariable("id") Long id) {
        RoleDto data = service.getById(id);
        return getResponse200(data);
    }

    @PostMapping(value = "/save")
    public BaseResponse save(@RequestBody RoleDto dto) {
        try {
            RoleDto data = service.save(dto);
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
            logger.error(e.getMessage());
            return false;
        }
    }

    @PostMapping(value = "/get-page")
    public Page<RoleDto> getPage(@RequestBody BaseSearch dto) {
        return service.getPage(dto);
    }

}
