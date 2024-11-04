package com.ja.finalproject.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.finalproject.dto.HobbyCategoryDto;
import com.ja.finalproject.dto.UserDto;
import com.ja.finalproject.dto.UserHobbyDto;
import com.ja.finalproject.user.mapper.UserSqlMapper;

@Service
public class UserService {
    
    @Autowired
    private UserSqlMapper userSqlMapper;
    public void addUser(UserDto userDto,List<Integer> hobbyIdList){
        userSqlMapper.createUser(userDto);
        int lastUserPk = userDto.getId();
        for(int hobbyId : hobbyIdList){
            UserHobbyDto userHobbyDto = new UserHobbyDto();
            userHobbyDto.setUserId(lastUserPk);
            userHobbyDto.setHobbyId(hobbyId);
            userSqlMapper.createUserHobby(userHobbyDto);
        }
    }

    public UserDto getUserByUserIdAndPassword(UserDto user){
        return userSqlMapper.findByUserIdAndPassword(user);
    }

    public List<HobbyCategoryDto> getHobbyList(){
        return userSqlMapper.findHobbyCategoryAll();
    }
}
