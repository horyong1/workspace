package com.ja.finalproject.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ja.finalproject.dto.RestResponseDto;
import com.ja.finalproject.dto.UserDto;
import com.ja.finalproject.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("api/user")
public class RestUserController {
    @Autowired
    private UserService userService;


    @RequestMapping("getSessionId")
    public RestResponseDto getSessionId(HttpSession session){
        RestResponseDto responseDto = new RestResponseDto();
        
        UserDto userDto = (UserDto)session.getAttribute("sessionUserInfo");
        responseDto.setResult("success");

        if(userDto == null){
            responseDto.setResult("fail");
            responseDto.setReason("인증 되지 않았습니다.");
        }else{
            responseDto.add("id",userDto.getId());
        }
        
        return responseDto;
    }

    @RequestMapping("checkIdDuplication")
    public RestResponseDto checkIdDuplication(@RequestParam("userId") String userId){
        RestResponseDto result = new RestResponseDto();
        result.setResult("success");
        result.add("isExist", userService.existsByUserId(userId));
        return result;
    }
}
