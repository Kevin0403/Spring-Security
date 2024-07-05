package org.example.signupwithsecurity.controller;


import org.example.signupwithsecurity.entity.Role;
import org.example.signupwithsecurity.entity.User;
import org.example.signupwithsecurity.entity.UserInfo;
import org.example.signupwithsecurity.repository.RoleRepository;
import org.example.signupwithsecurity.repository.UserInfoRepository;
import org.example.signupwithsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    UserInfoRepository userInfoRepository;
    UserRepository userRepository;
    RoleRepository roleRepository;

    @Autowired
    public UserController(UserInfoRepository userInfoRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.userInfoRepository = userInfoRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostMapping("")
    public UserInfo addUser(@RequestBody UserInfo userInfo) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User(userInfo.getUsername(), encoder.encode(userInfo.getPassword()));
        List<Role> roles = new ArrayList<>();
        for (Role role : userInfo.getRoles()) {
            Role r = roleRepository.findByName(role.getName()).orElse(null);
            if(r != null) {
                System.out.println("Hellow");
                roles.add(r);
            }
        }
        user.setRoles(roles);
        userRepository.save(user);
        userInfo.setUser(user);
        userInfoRepository.save(userInfo);
        return userInfo;
    }

    @GetMapping("")
    public UserInfo getUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println("username: " + username);
        UserInfo ui =  userInfoRepository.findUserInfoByUserName(username);
        System.out.println("ui: " + ui);
        return ui;
    }
}
