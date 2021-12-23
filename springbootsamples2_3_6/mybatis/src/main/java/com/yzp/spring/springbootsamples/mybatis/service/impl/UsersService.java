package com.yzp.spring.springbootsamples.mybatis.service.impl;

import com.yzp.spring.springbootsamples.mybatis.mapper.UserDao;
import com.yzp.spring.springbootsamples.mybatis.model.User;
import com.yzp.spring.springbootsamples.mybatis.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersService implements IUsersService {

    @Autowired
    private UserDao userDao;

    //添加用户
    @Transactional
    @Override
    public void addUsers(User users) throws Exception{
        userDao.insert(users);
    }
    @Transactional
    @Override
    public User selectById(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public List<User> getByKeyWord(String keyWord) {
        return userDao.getByKeyWord(keyWord);
    }

}
