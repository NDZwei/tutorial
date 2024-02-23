package com.ndz.app.dto;

import com.ndz.app.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/*
    author: NMDuc
    created_at: 2/23/2024
    github: https://github.com/NDZwei
*/
@Getter
@Setter
@NoArgsConstructor
public class UserDto extends BaseDto {
    private String username;
    private String password;
    private String confirmPassword;
    private String oldPassword;
    private String email;
    private List<RoleDto> roles = new ArrayList<>();

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        if(!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(item -> {
                this.roles.add(new RoleDto(item));
            });
        }
    }
}
