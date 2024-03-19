package com.ndz.app.dto;

import com.ndz.app.entity.Parameter;
import com.ndz.app.utils.EnumClass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/*
    author: NMDuc
    created_at: 3/16/2024
    github: https://github.com/NDZwei
*/
@NoArgsConstructor
@Getter
@Setter
public class ParameterDto {
    private Long id;
    private String code;
    private String name;
    private EnumClass.PARAMETER_TYPE type;

    public ParameterDto(Parameter entity) {
        this.id = entity.getId();
        this.code = entity.getCode();
        this.name = entity.getName();
        this.type = entity.getType();
    }

}
