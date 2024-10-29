package com.ja.finalproject.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.finalproject.dto.UserDto;
import com.ja.finalproject.user.mapper.UserSqlMapper;

@Service
public class UserService {
    
    @Autowired
    private UserSqlMapper userMapper;

    public void addUser(UserDto user){
        userMapper.createUser(user);
    }

    public UserDto getUserByUserIdAndPassword(UserDto user){
        return userMapper.findByUserIdAndPassword(user);
    }
}
