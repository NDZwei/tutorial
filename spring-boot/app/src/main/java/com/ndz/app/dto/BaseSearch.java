package com.ndz.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
    author: NMDuc
    created_at: 2/23/2024
    github: https://github.com/NDZwei
*/
@Getter
@Setter
@NoArgsConstructor
public class BaseSearch {
    private Integer pageIndex;
    private Integer pageSize;
    private String textSearch;
    private String email;
    private String emailType;
    private String statusSendMail;
    private Integer numberRecord;
}
