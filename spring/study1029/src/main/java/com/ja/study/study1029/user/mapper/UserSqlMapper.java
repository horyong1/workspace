package com.ja.study.study1029.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ja.study.study1029.user.dto.UserDto;

@Mapper
public interface UserSqlMapper {

    void createUser(UserDto userDto);
    
}
