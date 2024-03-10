package com.ndz.app.controller;

import com.ndz.app.dto.AdministrativeUnitDto;
import com.ndz.app.dto.BaseResponse;
import com.ndz.app.dto.UserDto;
import com.ndz.app.service.AdministrativeUnitService;
import com.ndz.app.service.NotificationService;
import com.ndz.app.service.UserService;
import org.springframework.web.bind.annotation.*;

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
    private UserService userService;

    @Resource
    private NotificationService notificationService;

    @Resource
    private AdministrativeUnitService administrativeUnitService;

    @GetMapping(value = "/administrative-unit/all")
    public BaseResponse getAll() {
        try {
            List<AdministrativeUnitDto> data = administrativeUnitService.getAllProvince();
            return getResponse200(data);
        } catch (Exception e) {
            return null;
        }
    }

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
