package com.ja.finalproject.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {NullPointerException.class})
    public String handleNullPointerException(NullPointerException e, Model model){

        System.out.println(e.getMessage());

        model.addAttribute("errorMessage", "아이디 또는 비밀번호가 잘못되었습니다.");
        return "user/loginFail";

    }
}
