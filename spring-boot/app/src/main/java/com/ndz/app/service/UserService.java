package com.ndz.app.service;

import com.ndz.app.dto.UserDto;

import java.util.List;
import java.util.UUID;

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

    UserDto register(UserDto dto);

    UserDto saveUser(UserDto dto);

    Boolean activeMail(UUID id);

    Boolean delete(Long id);

    Boolean voided(Long id);

    Boolean checkUsername(String username);

    Boolean checkEmail(String email);

    Boolean checkUserAndEmail(String username, String email);

    Boolean changePassword(UserDto dto);
}
