package com.ja.finalproject.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.finalproject.dto.UserDto;
import com.ja.finalproject.user.mapper.UserMapper;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void addUser(UserDto user){
        userMapper.createUser(user);
    }

    public void loginCheck(UserDto user){
        userMapper.loginCheck(user);
    }
}
