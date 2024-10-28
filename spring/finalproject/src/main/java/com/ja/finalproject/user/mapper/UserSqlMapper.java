package com.ja.finalproject.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ja.finalproject.dto.UserDto;

@Mapper
public interface UserSqlMapper {
    void createUser(UserDto userdto);
    void loginCheck(UserDto userdto);
}
