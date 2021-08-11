package com.yzp.spring.springsamples.mybatis.controller;

import com.yzp.spring.springsamples.mybatis.model.Users;
import com.yzp.spring.springsamples.mybatis.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    @Autowired
    private IUsersService usersService;

    @PostMapping("addUsers")
    public Users addUsers(@ModelAttribute Users users) {
        try {
            usersService.addUsers(users);
        } catch (Exception e) {
            System.out.println(e);
        }
        return users;
    }
}
