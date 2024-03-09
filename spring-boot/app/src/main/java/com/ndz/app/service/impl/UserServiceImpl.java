package com.ndz.app.service.impl;

import com.ndz.app.dto.RoleDto;
import com.ndz.app.dto.UserDto;
import com.ndz.app.entity.Notification;
import com.ndz.app.entity.Person;
import com.ndz.app.entity.Role;
import com.ndz.app.entity.User;
import com.ndz.app.repository.RoleRepository;
import com.ndz.app.repository.UserRepository;
import com.ndz.app.service.SendMailService;
import com.ndz.app.service.UserService;
import com.ndz.app.utils.EnumClass;
import com.ndz.app.utils.NDZUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/*
    author: NMDuc
    created_at: 2/22/2024
    github: https://github.com/NDZwei
*/
@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {
    @Resource
    private UserRepository repository;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private SendMailService sendMailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.getUserByUsername(username);
        if(ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException("User not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
        return null;
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = repository.findAll();
        return !CollectionUtils.isEmpty(users) ?
                users.stream().map(UserDto::new).collect(Collectors.toList()) : new ArrayList<>();
    }

    @Override
    public UserDto getById(Long id) {
        User user = repository.findById(id).orElse(null);
        return user != null ? new UserDto(user) : null;
    }

    @Override
    public UserDto getByUsername(String username) {
        User user = repository.getUserByUsername(username);
        return user != null ? new UserDto(user) : null;
    }

    @Override
    public UserDto getByEmail(String email) {
        User user = repository.getUserByEmail(email);
        return user != null ? new UserDto(user) : null;
    }

    @Override
    public UserDto register(UserDto dto) {
        if(dto == null) {
            return null;
        }
        Boolean isExitsUsername = this.checkUsername(dto.getUsername());
        if(isExitsUsername) {
            return null;
        }
        Boolean isExitsEmail = this.checkEmail(dto.getEmail());
        if(isExitsEmail) {
            return null;
        }
        User user = new User();
        Person person = new Person();
        person.setDisplayName(dto.getDisplayName());
        if(dto.getPassword() != null && dto.getConfirmPassword() != null
                && dto.getPassword().equals(dto.getConfirmPassword())) {
            user.setPassword(NDZUtils.getHashPassword(dto.getPassword()));
        } else {
            return null;
        }
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        List<Role> roles = new ArrayList<>();
        Role roleUser = roleRepository.getRoleByName(EnumClass.RoleEnum.ROLE_USER.name());
        if(roleUser != null) {
            roles.add(roleUser);
        }
        user.setRoles(roles);
        user.setPerson(person);
        person.setUser(user);
        // send mail active
        boolean resultSendMail = sendMailService.sendMailVerification(
                new Notification(user.getEmail(), UUID.randomUUID().toString(), dto.getDisplayName())
        );
        if(resultSendMail) {
            user = repository.save(user);
            return new UserDto(user);
        }
        return null;
    }

    @Override
    public UserDto saveUser(UserDto dto) {
        if(dto == null) {
            return null;
        }
        User user = null;
        Person person = null;
        boolean isNew = false;
        Boolean isExitsUsername = this.checkUsername(dto.getUsername());
        if(isExitsUsername) {
            return null;
        }
        Boolean isExitsEmail = this.checkEmail(dto.getEmail());
        if(isExitsEmail) {
            return null;
        }
        if(dto.getId() != null) {
            user = repository.findById(dto.getId()).orElse(null);
        }
        if(user == null) {
            user = new User();
            isNew = true;
        }
        person = user.getPerson();
        if(person == null) {
            person = new Person();
        }
        if(isNew && dto.getPassword() != null && dto.getConfirmPassword() != null
                && dto.getPassword().equals(dto.getConfirmPassword())) {
            user.setPassword(NDZUtils.getHashPassword(dto.getPassword()));
        }
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        Set<Role> roles = new HashSet<>();
        if(!CollectionUtils.isEmpty(dto.getRoles())) {
            Role role = null;
            for(RoleDto roleDto : dto.getRoles()) {
                if(roleDto.getId() != null) {
                    role = roleRepository.findById(roleDto.getId()).orElse(null);
                } else if(roleDto.getName() != null) {
                    role = roleRepository.getRoleByName(roleDto.getName());
                }
                if(role != null) {
                    roles.add(role);
                }
            }
        }
        if(!CollectionUtils.isEmpty(dto.getRoles())) {
            user.getRoles().clear();
            user.getRoles().addAll(roles);
        } else {
            user.getRoles().clear();
        }
        user.setPerson(person);
        person.setUser(user);
        user = repository.save(user);
        return new UserDto(user);
    }

    @Override
    public Boolean delete(Long id) {
        if(id != null) {
            User user = repository.findById(id).orElse(null);
            if(user != null) {
                repository.deleteById(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean voided(Long id) {
        if(id != null) {
            User user = repository.findById(id).orElse(null);
            if(user != null) {
                user.setVoided(true);
                repository.save(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean checkUsername(String username) {
        if(username != null) {
            User user = repository.getUserByUsername(username);
            return user != null;
        }
        return false;
    }

    @Override
    public Boolean checkEmail(String email) {
        if(email != null) {
            User user = repository.getUserByEmail(email);
            return user != null;
        }
        return false;
    }

    @Override
    public Boolean checkUserAndEmail(String username, String email) {
        User user = null;
        if(username != null) {
            user = repository.getUserByUsername(username);
            return user != null;
        }
        if(email != null) {
            user = repository.getUserByEmail(email);
            return user != null;
        }
        return false;
    }

    @Override
    public Boolean changePassword(UserDto dto) {
        User user = repository.findById(dto.getId()).orElse(null);
        if(user != null && dto.getOldPassword() != null && dto.getPassword() != null && dto.getConfirmPassword() != null
                && dto.getPassword().equals(dto.getConfirmPassword())
                && NDZUtils.matchesPassword(user.getPassword(), dto.getOldPassword())) {
            user.setPassword(NDZUtils.getHashPassword(dto.getPassword()));
            repository.save(user);
            return true;
        }
        return false;
    }
}
