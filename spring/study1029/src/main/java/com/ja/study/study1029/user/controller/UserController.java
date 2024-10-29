package com.ja.study.study1029.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping("loginPage")
    public String loginPage(){
        return "user/loginPage";
    }

    @RequestMapping("signUpPage")
    public String signup(){
        return "user/signUpPage";
    }

    @RequestMapping("signUpProcess")
    public String signUpProcess(){

        return"redirect:/user/loginPage";
    }


}
