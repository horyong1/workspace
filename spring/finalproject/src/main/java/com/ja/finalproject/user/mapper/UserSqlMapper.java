package com.ja.finalproject.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ja.finalproject.dto.UserDto;

@Mapper
public interface UserSqlMapper {
    void createUser(UserDto userdto);

    // insert, update, delete =  void
    // select = 적절한 리턴타입이 필요 
    UserDto findByUserIdAndPassword(UserDto userdto);
}
