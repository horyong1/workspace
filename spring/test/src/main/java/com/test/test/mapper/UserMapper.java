package com.test.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.test.test.model.User;

@Mapper
public interface UserMapper {
    List<User> selectAllUsers();
    void insertUser(User user);
}
