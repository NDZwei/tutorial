package com.ndz.app.dto;

import com.ndz.app.entity.AdministrativeUnit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/*
    author: NMDuc
    created_at: 3/6/2024
    github: https://github.com/NDZwei
*/
@Getter
@Setter
@NoArgsConstructor
public class AdministrativeUnitDto {
    private Long id;
    private String name;
    private Integer level;
    private AdministrativeUnitDto parent;
    private Long parentId;
    private List<AdministrativeUnitDto> children;

    public AdministrativeUnitDto(AdministrativeUnit entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.level = entity.getLevel();
        if(entity.getParent() != null) {
            this.parent = new AdministrativeUnitDto(entity.getParent());
            this.parentId = entity.getParent().getId();
        }
    }
}
