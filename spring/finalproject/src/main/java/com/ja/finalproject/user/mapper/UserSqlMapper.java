package com.ja.finalproject.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ja.finalproject.dto.HobbyCategoryDto;
import com.ja.finalproject.dto.MailAuthDto;
import com.ja.finalproject.dto.UserDto;
import com.ja.finalproject.dto.UserHobbyDto;

@Mapper
public interface UserSqlMapper {
    void createUser(UserDto userdto);

    // insert, update, delete =  void
    // select = 적절한 리턴타입이 필요 
    UserDto findByUserIdAndPassword(UserDto userdto);
    UserDto findById(int id);
    int countUserByUserId(String userId);

    // 취미 관련
    List<HobbyCategoryDto> findHobbyCategoryAll();
    void createUserHobby(UserHobbyDto userHobbyDto);

    // 메일 인증
    void createMailAuth(MailAuthDto mailAuthDto);
    void updateMailAuthComplete(String auth_key);
}
