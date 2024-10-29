package com.ja.study.study1029.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.study.study1029.user.dto.UserDto;
import com.ja.study.study1029.user.mapper.UserSqlMapper;

@Service
public class UserService {

    @Autowired
    private UserSqlMapper userSqlMapper;

    public void registerUser(UserDto userDto){
        userSqlMapper.createUser(userDto);
    }
}
