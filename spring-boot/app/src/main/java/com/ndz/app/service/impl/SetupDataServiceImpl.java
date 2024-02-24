package com.ndz.app.service.impl;

import com.ndz.app.dto.BaseResponse;
import com.ndz.app.dto.RoleDto;
import com.ndz.app.service.RoleService;
import com.ndz.app.service.SetupDataService;
import com.ndz.app.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/*
    author: NMDuc
    created_at: 2/24/2024
    github: https://github.com/NDZwei
*/
@Component
public class SetupDataServiceImpl implements SetupDataService {
    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Override
    public void createData() {

    }

    public void setUpRole(String name) {
        BaseResponse baseResponse = roleService.getRoleByName(name);
        if (!baseResponse.getCode().equals(NDZEnum.Response.SUCCESS.getStatus().toString())) {
            RoleDto dto = new RoleDto();
            dto.setName(name);
            try {
                roleService.saveRole(dto);
            } catch (Exception e) {
                logger.info("Error create role at: {}", new Date());
            }
        }
    }
}
