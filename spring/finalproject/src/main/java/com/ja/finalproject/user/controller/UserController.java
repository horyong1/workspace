package com.ja.finalproject.user.controller;

import java.util.List;

import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ja.finalproject.dto.UserDto;
import com.ja.finalproject.user.service.UserService;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
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
    public String registerPage(Model model) {
        model.addAttribute("hobbyList",userservice.getHobbyList());
        return "user/registerPage";
    }

    /*
     * 회원가입 로직 실행
     */
    @RequestMapping("registerProcess")
    public String registerProcess(UserDto params, @RequestParam("hobbyId") List<Integer> hobbyIdList){
        userservice.addUser(params, hobbyIdList);
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
        UserDto sessionUserInfo = 
            userservice.getUserByUserIdAndPassword(params).orElseThrow(()-> new NullPointerException("로그인 실패"));
        session.setAttribute("sessionUserInfo", sessionUserInfo);
        
        // userservice.getUserByUserIdAndPassword(params).ifPresent(sessionUserInfo ->{
        //     session.setAttribute("sessionUserInfo", sessionUserInfo);
        // });
        
        // if(sessionUserInfo==null){
        //     return "user/loginFail";
        // }
        
        // // 로그인 성공
        // session.setAttribute("sessionUserInfo", sessionUserInfo);

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
    
    // 이메일 인증 프로세스
    @RequestMapping("mailAuthProcess")
    public String mailAuthProcess(@RequestParam String key){
        userservice.authenticateMail(key);
        return"user/mailAuthComplete";
    }

    // 인터셉트 페이지
    @RequestMapping("sessionNullPage")
    public String sessionNullPage(){
        return"user/sessionNullPage";
    }

}
