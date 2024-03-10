package com.ndz.app.controller;

import com.ndz.app.dto.BaseResponse;
import com.ndz.app.dto.UserDto;
import com.ndz.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/*
    author: NMDuc
    created_at: 2/23/2024
    github: https://github.com/NDZwei
*/
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Resource
    private UserService service;

    @GetMapping(value = "/all")
    public BaseResponse getAll() {
        try {
            List<UserDto> data = service.getAll();
            return getResponse200(data);
        } catch (Exception e) {
            return getResponse500(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public BaseResponse getById(@PathVariable("id") Long id) {
        try {
            UserDto data = service.getById(id);
            return getResponse200(data);
        } catch (Exception e) {
            return getResponse500(e.getMessage());
        }
    }

    @PostMapping(value = "/save")
    public BaseResponse save(@RequestBody UserDto dto) {
        try {
            UserDto data = service.saveUser(dto);
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

    @DeleteMapping(value = "/delete/{id}")
    public Boolean voided(@PathVariable("id") Long id) {
        try {
            return service.voided(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @GetMapping(value = "/check-username")
    public Boolean checkUsername(@RequestParam String username) {
        try {
            return service.checkUsername(username);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @GetMapping(value = "/check-email")
    public Boolean checkEmail(@RequestParam String email) {
        try {
            return service.checkEmail(email);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @GetMapping(value = "/check-username-and-email")
    public Boolean checkUserAndEmail(@RequestParam String username, @RequestParam String email) {
        try {
            return service.checkUserAndEmail(username, email);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @PostMapping(value = "/change-password")
    public Boolean changePassword(@RequestBody UserDto dto) {
        try {
            return service.changePassword(dto);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }
}
