package com.ndz.app.dto.convert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
    author: NMDuc
    created_at: 3/9/2024
    github: https://github.com/NDZwei
*/
@Getter
@Setter
@NoArgsConstructor
public class ExcelToAdministrativeUnit {
    private String provinceCode;
    private String provinceName;
    private String districtCode;
    private String districtName;
    private String communeCode;
    private String communeName;
}
