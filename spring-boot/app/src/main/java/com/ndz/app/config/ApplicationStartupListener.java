package com.ndz.app.config;

import com.ndz.app.service.SetupDataService;
import com.ndz.app.utils.NDZUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/*
    author: NMDuc
    created_at: 2/24/2024
    github: https://github.com/NDZwei
*/
@Component
public class ApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent>, InitializingBean {
    @Resource
    private Environment env;

    @Resource
    private SetupDataService setupDataService;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        NDZUtils.IMAGE_PATH = env.getProperty("file.image_path");
        setupDataService.createData();
    }
}
