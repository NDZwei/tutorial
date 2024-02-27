package com.ndz.app.controller;

import com.ndz.app.dto.BaseResponse;
import com.ndz.app.dto.UserDto;
import com.ndz.app.service.NotificationService;
import com.ndz.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/*
    author: NMDuc
    created_at: 2/27/2024
    github: https://github.com/NDZwei
*/
@RestController
@RequestMapping(value = "/api/public")
public class PublicController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Resource
    private UserService userService;

    @Resource
    private NotificationService notificationService;

    @PostMapping(value = "/register")
    private BaseResponse register(@RequestBody UserDto dto) {
        try {
            UserDto data = userService.register(dto);
            return getResponse200(data);
        } catch (Exception e) {
            return getResponse500(e.getMessage());
        }
    }

    @GetMapping(value = "/verification-email")
    private void verificationEmail(@RequestParam String email, @RequestParam String emailToken) {
        notificationService.verificationEmail(email, emailToken);
    }
}
