package com.ja.finalproject.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping("loginPage")
    public String loginPage(){

        return"user/loginPage";
    }

    @RequestMapping("registerPage")
    public String registerPage() {
        
        return "user/registerPage";
    }
    
}
