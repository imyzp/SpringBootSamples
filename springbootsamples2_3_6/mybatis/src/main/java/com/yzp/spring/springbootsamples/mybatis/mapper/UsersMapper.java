package com.yzp.spring.springbootsamples.mybatis.mapper;

import com.yzp.spring.springbootsamples.mybatis.model.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper {
    //添加用户
    void addUsers(Users users);
}
