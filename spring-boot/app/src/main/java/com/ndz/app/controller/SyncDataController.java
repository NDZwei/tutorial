package com.ndz.app.controller;

import com.ndz.app.dto.BaseResponse;
import com.ndz.app.service.SyncDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*
    author: NMDuc
    created_at: 3/19/2024
    github: https://github.com/NDZwei
*/
@RestController
@RequestMapping(value = "/sync-data")
public class SyncDataController extends BaseController {
    @Resource
    private SyncDataService syncDataService;

    @GetMapping(value = "/administrative-unit/{id}")
    public BaseResponse syncAdministrativeById(@PathVariable Long id) {
        try {
            syncDataService.syncAdministrativeById(id);
            return getResponse200("Sync data administrative successfully");
        } catch (Exception e) {
            return getResponse500(e.getMessage());
        }
    }
}
