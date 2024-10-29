package com.ja.study.study1029.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ja.study.study1029.user.dto.UserDto;
import com.ja.study.study1029.user.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    // 로그인 페이지    
    @RequestMapping("loginPage")
    public String loginPage(){
        return "user/loginPage";
    }

    // 회원가입 페이지
    @RequestMapping("signUpPage")
    public String signup(){
        return "user/signUpPage";
    }

    // 회원가입 등록
    @RequestMapping("signUpProcess")
    public String signUpProcess(UserDto params){
        userService.registerUser(params);
        return"redirect:/user/loginPage";
    }


}
