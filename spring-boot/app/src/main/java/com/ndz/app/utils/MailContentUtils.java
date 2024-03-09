package com.ndz.app.utils;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/*
    author: NMDuc
    created_at: 2/26/2024
    github: https://github.com/NDZwei
*/
public class MailContentUtils {
    public static String buildContentVerification(TemplateEngine templateEngine, String actionUrl, String displayName) {
        Context context = new Context();
        context.setVariable("action_url", actionUrl);
        context.setVariable("display_name", displayName);
        return templateEngine.process("mail/verification.html", context);
    }
}
