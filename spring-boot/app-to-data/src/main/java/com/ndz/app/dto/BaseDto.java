package com.ndz.app.dto;

import com.ndz.app.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/*
    author: NMDuc
    created_at: 2/21/2024
    github: https://github.com/NDZwei
*/
@Getter
@Setter
@NoArgsConstructor
public class BaseDto {
    protected Long id;
    protected Date createdAt;
    protected String createdBy;
    protected Date updatedAt;
    protected String updatedBy;
    protected Boolean voided;
    protected String voidedBy;
    protected Date voidedAt;

    public BaseDto(BaseEntity entity) {
        this.id = entity.getId();
        this.createdAt = entity.getCreatedAt();
        this.createdBy = entity.getCreatedBy();
        this.updatedAt = entity.getUpdatedAt();
        this.updatedBy = entity.getUpdatedBy();
        this.voided = entity.getVoided();
        this.voidedBy = entity.getVoidedBy();
        this.voidedAt = entity.getVoidedAt();
    }
}
