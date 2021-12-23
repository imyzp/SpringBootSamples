package com.yzp.spring.springbootsamples.mybatis.controller;

import com.yzp.spring.springbootsamples.mybatis.model.User;
import com.yzp.spring.springbootsamples.mybatis.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {
    @Autowired
    private IUsersService usersService;

    @PostMapping("/user")
    public User addUsers(@RequestBody User users) throws Exception {
        usersService.addUsers(users);
        return users;
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<?> user(@PathVariable(value = "id")Integer id){
        User user = usersService.selectById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("/user/{keyWord}")
    public ResponseEntity<?> getByKeyWord(@PathVariable(value = "keyWord") String keyWord)
    {
        List<User> list = usersService.getByKeyWord(keyWord);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
