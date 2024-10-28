package com.ja.finalproject.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ja.finalproject.dto.UserDto;

@Mapper
public interface UserMapper {
    void addUser(UserDto userdto);
    void loginCheck(UserDto userdto);
}
