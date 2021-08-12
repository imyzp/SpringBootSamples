package com.yzp.spring.springbootsamples.mybatis.service;

import com.yzp.spring.springbootsamples.mybatis.model.Users;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED)
public interface IUsersService {
    //添加用户
    void addUsers(Users users) throws Exception;
}
