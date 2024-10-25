package com.test.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.test.mapper.UserMapper;
import com.test.test.model.User;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers(){
        return userMapper.selectAllUsers();
    }

    public void addUser(User user) {
        userMapper.insertUser(user);
    }
}
