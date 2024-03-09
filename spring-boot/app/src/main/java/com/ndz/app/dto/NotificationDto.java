package com.ndz.app.dto;

import com.ndz.app.entity.Notification;
import com.ndz.app.utils.EnumClass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
    author: NMDuc
    created_at: 2/26/2024
    github: https://github.com/NDZwei
*/
@Getter
@Setter
@NoArgsConstructor
public class NotificationDto extends BaseDto {
    private String subject;
    private String content;
    private String email;
    private String displayName;
    private EnumClass.EMAIL_TYPE emailType;
    private EnumClass.STATUS_SEND statusSendMail;

    public NotificationDto(Notification entity) {
        this.id = entity.getId();
        this.subject = entity.getSubject();
        this.content = entity.getContent();
        this.email = entity.getEmail();
        this.displayName = entity.getDisplayName();
        this.emailType = entity.getEmailType();
        this.statusSendMail = entity.getStatusSendMail();
        this.createdAt = entity.getCreatedAt();
    }
}
