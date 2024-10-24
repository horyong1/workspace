package com.ja.test12.controller;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ja.test12.service.TestService;


@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private Scanner scn;

    
    // public TestController (TestService testService){
    //     this.testService = testService;
    // }

    @RequestMapping("/main")
    public String hello() {

        testService.someMethod();
        
        return "main";
    }
    
}
