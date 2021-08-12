package com.yzp.spring.springbootsamples.mybatis.service.impl;

import com.yzp.spring.springbootsamples.mybatis.service.IUsersService;
import com.yzp.spring.springbootsamples.mybatis.mapper.UsersMapper;
import com.yzp.spring.springbootsamples.mybatis.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UsersService implements IUsersService {

    @Autowired
    private UsersMapper usersMapper;

    //添加用户
    @Transactional
    @Override
    public void addUsers(Users users) throws Exception{
        System.out.println("---------添加用户-------"+new Date().getTime());
        usersMapper.addUsers(users);
    }

}
