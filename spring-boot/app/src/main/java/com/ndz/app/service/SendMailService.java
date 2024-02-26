package com.ndz.app.service;

import com.ndz.app.entity.Notification;

/*
    author: NMDuc
    created_at: 2/26/2024
    github: https://github.com/NDZwei
*/
public interface SendMailService {
    Boolean sendMailActiveAccount(Notification notification);
}
