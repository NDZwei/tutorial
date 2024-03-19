package com.ndz.app.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
    author: NMDuc
    created_at: 3/19/2024
    github: https://github.com/NDZwei
*/
@NoArgsConstructor
@Getter
@Setter
public class JwtResponse {
    private String access_token;
    private String refresh_token;
    private String username;
}
