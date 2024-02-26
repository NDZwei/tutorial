package com.ndz.app.utils;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/*
    author: NMDuc
    created_at: 2/26/2024
    github: https://github.com/NDZwei
*/
public class MailContentUtils {
    public static String buildContentVerification(TemplateEngine templateEngine) {
        Context context = new Context();
        return templateEngine.process("mail/verification.html", context);
    }
}
