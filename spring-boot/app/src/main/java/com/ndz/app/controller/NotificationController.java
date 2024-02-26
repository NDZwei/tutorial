package com.ndz.app.controller;

import com.ndz.app.dto.BaseResponse;
import com.ndz.app.dto.BaseSearch;
import com.ndz.app.dto.NotificationDto;
import com.ndz.app.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/*
    author: NMDuc
    created_at: 2/26/2024
    github: https://github.com/NDZwei
*/
@RestController
@RequestMapping(value = "/notification")
public class NotificationController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);
    @Resource
    private NotificationService service;

    @PostMapping(value = "/all")
    public BaseResponse getAll(@RequestBody BaseSearch baseSearch) {
        try {
            List<NotificationDto> data = service.getAllByCondition(baseSearch);
            return getResponse200(data);
        } catch (Exception e) {
            return getResponse500(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public BaseResponse getById(@PathVariable Long id) {
        try {
            NotificationDto data = service.getById(id);
            return getResponse200(data);
        } catch (Exception e) {
            return getResponse500(e.getMessage());
        }
    }

    @GetMapping(value = "/seen-notification/{id}")
    public Boolean seenNotification(@PathVariable Long id) {
        try {
            return service.seenNotification(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @DeleteMapping(value = "/{id}")
    public Boolean delete(@PathVariable Long id) {
        try {
            return service.delete(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @PostMapping(value = "/get-page")
    public Page<NotificationDto> getPage(@RequestBody BaseSearch baseSearch) {
        try {
            return service.getPage(baseSearch);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
