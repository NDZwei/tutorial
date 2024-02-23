package com.ndz.app.repository;

import com.ndz.app.dto.UserDto;
import com.ndz.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    author: NMDuc
    created_at: 2/22/2024
    github: https://github.com/NDZwei
*/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByUsername(String username);

    User getUserByEmail(String email);
}
