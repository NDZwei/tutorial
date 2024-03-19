package com.ndz.app.entity;

import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.util.Date;

/*
    author: NMDuc
    created_at: 2/21/2024
    github: https://github.com/NDZwei
*/
@NoArgsConstructor
public class EntityAuditListener {
    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return "admin";
    }
    @PrePersist
    public void beforePrePersist(AuditableEntity auditableEntity) {
        auditableEntity.setCreatedAt(new Date());
        auditableEntity.setUpdatedAt(new Date());
        auditableEntity.setCreatedBy(getUsername());
    }

    // Before update
    @PreUpdate
    public void beforeMerge(AuditableEntity auditableEntity) {
        auditableEntity.setUpdatedAt(new Date());
        auditableEntity.setUpdatedBy(getUsername());
    }

    @PreRemove
    public void beforeRemove(AuditableEntity auditableEntity) {
        auditableEntity.setVoidedBy(getUsername());
        auditableEntity.setVoidedAt(new Date());
    }
}
