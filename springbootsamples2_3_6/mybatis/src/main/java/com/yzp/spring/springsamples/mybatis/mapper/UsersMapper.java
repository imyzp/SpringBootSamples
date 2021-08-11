package com.yzp.spring.springsamples.mybatis.mapper;

import com.yzp.spring.springsamples.mybatis.model.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper {
    //添加用户
    void addUsers(Users users);
}
