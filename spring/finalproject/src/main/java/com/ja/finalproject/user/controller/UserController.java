package com.ja.finalproject.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ja.finalproject.dto.UserDto;
import com.ja.finalproject.user.service.UserService;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userservice;

    // 로그인 페이지
    @RequestMapping("loginPage")
    public String loginPage(){
        return"user/loginPage";
    }

    // 회원 가입 페이지
    @RequestMapping("registerPage")
    public String registerPage() {
        return "user/registerPage";
    }

    // 회원가입 추가
    @RequestMapping("registerProcess")
    public String registerProcess(UserDto params){
        userservice.addUser(params);
        System.out.println(params);
        return "user/registerComplete";
    }

    @RequestMapping()
    public String login(UserDto params){
        userservice.loginCheck(params);
        return "/board/list";
    }


    
}
