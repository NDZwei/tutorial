package com.ndz.app.entity;

/*
    author: NMDuc
    created_at: 2/26/2024
    github: https://github.com/NDZwei
*/

import com.ndz.app.utils.EnumClass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tbl_notification")
@Getter
@Setter
@NoArgsConstructor
public class Notification extends BaseEntity {
    @Column(name = "subject", columnDefinition = "TEXT")
    private String subject;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "email")
    private String email;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "email_token")
    private String emailToken;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EnumClass.EMAIL_TYPE emailType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_send")
    private EnumClass.STATUS_SEND statusSendMail;

    @Column(name = "is_seen")
    private Boolean isSeen = false;

    public Notification(String email, String emailToken, String displayName) {
        this.email = email;
        this.emailToken = emailToken;
        this.displayName = displayName;
    }
}
