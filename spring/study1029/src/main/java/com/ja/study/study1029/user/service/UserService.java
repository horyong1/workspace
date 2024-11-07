package com.ja.study.study1029.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.study.study1029.user.dto.UserDto;
import com.ja.study.study1029.user.dto.UserHobbyDto;
import com.ja.study.study1029.user.dto.HobbyCategoryDto;
import com.ja.study.study1029.user.mapper.UserSqlMapper;

@Service
public class UserService {

    @Autowired
    private UserSqlMapper userSqlMapper;

    public void registerUser(UserDto userDto,List<Integer> hobbyIdList){
        userSqlMapper.createUser(userDto);
        int userId = userDto.getId();
        for(int hobbyId : hobbyIdList){
            UserHobbyDto userHobbyDto = new UserHobbyDto();
            userHobbyDto.setUserId(userId);
            userHobbyDto.setHobbyId(hobbyId);
            userSqlMapper.createUserHobby(userHobbyDto);
        }
    }

    public UserDto getUserByUserIdAndPassword(UserDto userDto){
        return userSqlMapper.findByUserIdAndPassword(userDto);
    }

    public List<HobbyCategoryDto> getHobbyCategoryList(){
        return userSqlMapper.findByHobbyCategoryAll();
    }

    public UserDto getUserInfo(int id){
        return userSqlMapper.findById(id);
    }
}
