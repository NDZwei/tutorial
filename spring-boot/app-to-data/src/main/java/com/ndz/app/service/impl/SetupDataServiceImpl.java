package com.ndz.app.service.impl;

import com.ndz.app.dto.RoleDto;
import com.ndz.app.dto.UserDto;
import com.ndz.app.service.RoleService;
import com.ndz.app.service.SetupDataService;
import com.ndz.app.service.UserService;
import com.ndz.app.utils.EnumClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/*
    author: NMDuc
    created_at: 2/24/2024
    github: https://github.com/NDZwei
*/
@Component
public class SetupDataServiceImpl implements SetupDataService {
    private static final Logger logger = LoggerFactory.getLogger(SetupDataServiceImpl.class);

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Override
    public void createData() {
        // Create role
        this.setUpRole(EnumClass.RoleEnum.ROLE_ADMIN.name(), EnumClass.RoleEnum.ROLE_ADMIN.getDescription());
        this.setUpRole(EnumClass.RoleEnum.ROLE_INSTRUCTOR.name(), EnumClass.RoleEnum.ROLE_INSTRUCTOR.getDescription());
        this.setUpRole(EnumClass.RoleEnum.ROLE_USER.name(), EnumClass.RoleEnum.ROLE_USER.getDescription());
        // Create user
        this.setUpUser("admin", "123456", "admin@gmail.com", List.of(new RoleDto(EnumClass.RoleEnum.ROLE_ADMIN.name(), EnumClass.RoleEnum.ROLE_ADMIN.getDescription())));
        this.setUpUser("instructor", "123456", "instructor@gmail.com",  List.of(new RoleDto(EnumClass.RoleEnum.ROLE_INSTRUCTOR.name(), EnumClass.RoleEnum.ROLE_INSTRUCTOR.getDescription())));
        this.setUpUser("customer", "123456", "customer@gmail.com",  List.of(new RoleDto(EnumClass.RoleEnum.ROLE_USER.name(), EnumClass.RoleEnum.ROLE_USER.getDescription())));
    }

    public void setUpRole(String name, String description) {
        RoleDto role = roleService.getByName(name);
        if (role == null) {
            RoleDto dto = new RoleDto();
            dto.setName(name);
            dto.setDescription(description);
            try {
                roleService.save(dto);
            } catch (Exception e) {
                logger.info("Error create role at: {}", new Date());
            }
        }
    }

    public void setUpUser(String username, String password, String email, List<RoleDto> roles) {
        if(!userService.checkUserAndEmail(username, email)) {
            UserDto request = new UserDto();
            request.setUsername(username);
            request.setPassword(password);
            request.setConfirmPassword(password);
            request.setEmail(email);
            request.setRoles(roles);
            try {
                userService.saveUser(request);
            } catch (Exception e) {
                logger.info("Error create user at: {}", new Date());
            }
        }
    }
}
