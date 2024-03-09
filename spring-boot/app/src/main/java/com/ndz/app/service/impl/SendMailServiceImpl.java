package com.ndz.app.service.impl;

import com.ndz.app.entity.Notification;
import com.ndz.app.repository.NotificationRepository;
import com.ndz.app.service.NotificationService;
import com.ndz.app.service.SendMailService;
import com.ndz.app.utils.EnumClass;
import com.ndz.app.utils.MailContentUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;

import javax.annotation.Resource;
import java.util.UUID;

/*
    author: NMDuc
    created_at: 2/26/2024
    github: https://github.com/NDZwei
*/
@Service
@Transactional
@EnableAsync
public class SendMailServiceImpl implements SendMailService {
    private final static Logger logger = LoggerFactory.getLogger(SendMailServiceImpl.class);
    @Resource
    private TemplateEngine templateEngine;

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private NotificationRepository notificationRepository;

    @Override
    public Boolean sendMailVerification(Notification notification) {
        String email = notification.getEmail() != null ? "?email=" + notification.getEmail() : "";
        String emailToken = notification.getEmailToken() != null ? "&emailToken=" + notification.getEmailToken() : "";
        String actionUrl = EnumClass.SERVER_URL.LOCALHOST.getUrl() + "/public/verification-email" + email + emailToken;
        String content = MailContentUtils.buildContentVerification(templateEngine, actionUrl, "NDuc");
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(notification.getEmail());
            messageHelper.setSubject("[NDZ] Email verification for register");
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(mimeMessagePreparator);
            notification.setSubject("[NDZ] Email verification for register");
            notification.setStatusSendMail(EnumClass.STATUS_SEND.SUCCESS);
            notification.setEmailType(EnumClass.EMAIL_TYPE.REGISTER);
            notification.setContent(content);
            notification.setEmailToken(notification.getEmailToken());
            notificationRepository.save(notification);
            return true;
        } catch (Exception e) {
            notification.setStatusSendMail(EnumClass.STATUS_SEND.ERROR);
            logger.error(e.getMessage());
            notificationRepository.save(notification);
            return false;
        }
    }
}
