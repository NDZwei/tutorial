package com.ndz.app.dto;

import com.ndz.app.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
    author: NMDuc
    created_at: 2/21/2024
    github: https://github.com/NDZwei
*/
@Getter
@Setter
@NoArgsConstructor
public class RoleDto extends BaseDto {
    private String name;
    private String description;

    public RoleDto(Role entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
    }
}
