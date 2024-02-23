package com.ndz.app.service;

import com.ndz.app.dto.UserDto;

import java.util.List;

/*
    author: NMDuc
    created_at: 2/22/2024
    github: https://github.com/NDZwei
*/
public interface UserService {
    List<UserDto> getAll();

    UserDto getById(Long id);

    UserDto getByUsername(String username);

    UserDto getByEmail(String email);

    UserDto saveUser(UserDto dto);

    Boolean delete(Long id);

    Boolean voided(Long id);

    Boolean checkUsername(String username);

    Boolean checkEmail(String email);

    Boolean checkUserAndEmail(String username, String email);

    Boolean changePassword(UserDto dto);
}
