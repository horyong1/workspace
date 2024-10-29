package com.ja.finalproject.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ja.finalproject.dto.UserDto;
import com.ja.finalproject.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userservice;

    /*
     * 로그인 페이지
     */
    @RequestMapping("loginPage")
    public String loginPage(){
        return"user/loginPage";
    }

    /*
     * 회원가입 페이지
     */
    @RequestMapping("registerPage")
    public String registerPage() {
        return "user/registerPage";
    }

    /*
     * 회원가입 로직 실행
     */
    @RequestMapping("registerProcess")
    public String registerProcess(UserDto params){
        userservice.addUser(params);
        System.out.println(params);
        return "user/registerComplete";
    }

    /**
     * 로그인 정보 체크 
     * @param UserDto
     * @return redirect:/board/list
     */
    @RequestMapping("loginProcess")
    public String loginProcess(UserDto params, HttpSession session){
        System.out.println("정보 >>>> " + params);
        UserDto sessionUserInfo = userservice.getUserByUserIdAndPassword(params);
        
        if(sessionUserInfo==null){
            return "user/loginFail";
        }
        
        // 로그인 성공
        session.setAttribute("sessionUserInfo", sessionUserInfo);

        return "redirect:/board/mainPage";
    }

    /*
     * 로그아웃 
     */
    @RequestMapping("logOutProcess")
    public String logOutProcess(HttpSession session){
        session.invalidate();
        // session.removeAttribute("sessionUserInfo");
        return "redirect:/board/mainPage";
    }

    // @RequestMapping("test")
    // public String test(HttpServletRequest request, HttpSession session){

    //     request.setAttribute("yyy", 30);
    //     request.getAttribute(null);

    //     session.setAttribute("qqq", 50);

    //     return "qwer";
    // }
    
}
