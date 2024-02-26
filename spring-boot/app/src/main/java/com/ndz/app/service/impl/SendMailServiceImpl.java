package com.ndz.app.service.impl;

import com.ndz.app.entity.Notification;
import com.ndz.app.service.SendMailService;
import com.ndz.app.utils.MailContentUtils;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;

import javax.annotation.Resource;

/*
    author: NMDuc
    created_at: 2/26/2024
    github: https://github.com/NDZwei
*/
@Service
@Transactional
public class SendMailServiceImpl implements SendMailService {
    @Resource
    private TemplateEngine templateEngine;

    @Override
    public Boolean sendMailActiveAccount(Notification notification) {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(notification.getEmail());
            messageHelper.setSubject("[NDZ] Verification account");
            messageHelper.setText(MailContentUtils.buildContentVerification(templateEngine), true);

        };
        return true;
    }
}
