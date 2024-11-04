package com.ja.study.study1029.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ja.study.study1029.user.dto.UserDto;
import com.ja.study.study1029.user.service.UserService;

import jakarta.servlet.http.HttpSession;

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

    // 로그인 프로세스
    @RequestMapping("loginProcess")
    public String loginProcess(HttpSession session ,UserDto params){
        UserDto sessionUserInfo = userService.getUserByUserIdAndPassword(params);
        System.out.println(sessionUserInfo);
        if(sessionUserInfo == null){
            return "user/loginFail";
        }
        session.setAttribute("sessionUserInfo", sessionUserInfo);
        return "redirect:/board/mainPage";
    }

    // 로그아웃
    @RequestMapping("logOut")
    public String logOut(HttpSession session){
        session.invalidate();
        return "redirect:/board/mainPage";
    }


    // 회원가입 페이지
    @RequestMapping("signUpPage")
    public String signup(Model model){
        model.addAttribute("hobbyList", userService.getHobbyCategoryList());
        return "user/signUpPage";
    }

    // 회원가입 등록
    @RequestMapping("signUpProcess")
    public String signUpProcess(UserDto params, @RequestParam("hobby")List<Integer> hobbyIdList){
        userService.registerUser(params,hobbyIdList);
        return"redirect:/user/loginPage";
    }


}
