package com.ja.study.study1029.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ja.study.study1029.user.dto.HobbyCategoryDto;
import com.ja.study.study1029.user.dto.UserDto;
import com.ja.study.study1029.user.dto.UserHobbyDto;

@Mapper
public interface UserSqlMapper {

    void createUser(UserDto userDto);
    UserDto findByUserIdAndPassword(UserDto userDto);
    UserDto findById(int id);

    List<HobbyCategoryDto> findByHobbyCategoryAll();
    void createUserHobby(UserHobbyDto UserHobbyDto);
}
