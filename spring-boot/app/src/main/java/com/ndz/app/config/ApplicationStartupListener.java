package com.ndz.app.config;

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
    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartupListener.class);

    @Resource
    private Environment env;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    }
}
