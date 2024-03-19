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
    private String code;
    private String name;
    private Integer level;
    private AdministrativeUnitDto parent;
    private Long parentId;
    private List<AdministrativeUnitDto> children;

    public AdministrativeUnitDto(AdministrativeUnit entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.code = entity.getCode();
        this.level = entity.getLevel();
        if(entity.getParent() != null) {
            this.parent = new AdministrativeUnitDto(entity.getParent());
        }
    }

    public AdministrativeUnitDto(Long id, String code, String name, Integer level, Long parentId) {
        this.code = code;
        this.name = name;
        this.level = level;
        AdministrativeUnitDto parent = new AdministrativeUnitDto();
        parent.setId(parentId);
        this.parent = parent;
    }
}
