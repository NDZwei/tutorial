package com.ndz.app.entity;

import com.ndz.app.utils.EnumClass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/*
    author: NMDuc
    created_at: 3/16/2024
    github: https://github.com/NDZwei
*/
@Entity
@Table(name = "tbl_parameter")
@Getter
@Setter
@NoArgsConstructor
public class Parameter extends BaseEntity{
    @Column(name = "code", unique = true, nullable = false, length = 10)
    private String code;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EnumClass.PARAMETER_TYPE type;
}
