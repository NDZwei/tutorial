package com.ndz.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/*
    author: NMDuc
    created_at: 2/21/2024
    github: https://github.com/NDZwei
*/
@Getter
@Setter
@MappedSuperclass
@EntityListeners({EntityAuditListener.class})
public abstract class AuditableEntity {
    @Column(name = "created_at")
    protected Date createdAt;

    @Column(name = "created_by")
    protected String createdBy;

    @Column(name = "updated_at")
    protected Date updatedAt;

    @Column(name = "updated_by")
    protected String updatedBy;

    @Column(name = "voided")
    protected Boolean voided = false;

    @Column(name = "voided_by")
    protected String voidedBy;

    @Column(name = "voided_at")
    protected Date voidedAt;
}
