package com.yzp.spring.springbootsamples.mybatis.service;

import com.yzp.spring.springbootsamples.mybatis.model.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
public interface IUsersService {
    //添加用户
    void addUsers(User users) throws Exception;

    User selectById(Integer id);

    List<User> getByKeyWord(String keyWord);
}
