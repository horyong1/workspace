package com.ja.p1025_1.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


// 클라이언트로 부터 request를 받아서 처리하는 클래스
@Controller
@RequestMapping("user/shop")
public class QWERController {

    @RequestMapping("login")
    public String qwer(
        @RequestParam(value ="name", required = false) String value1,
        @RequestParam(value ="id", required = false) String value2,
        @RequestParam(value ="pwd",required = false, defaultValue = "1") String value3){
        
            System.out.println(value1);
        
            System.out.println(value2);
        
            System.out.println(value3);
        
            return "aaa";
    }

    @RequestMapping("list")
    public String someMethod(StudentDto params){
        System.out.println("list");
        System.out.println(params);
        return"aaa2";
    }

    @RequestMapping("write")
    public String register(){
        System.out.println();
        System.out.println();

        return "write";
    }

    
    @RequestMapping("register")
    public ModelAndView register2( 
        @RequestParam(value = "title") String title,
        @RequestParam(value = "content") String content){
        System.out.println(title);
        System.out.println(content);
        Map<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("content", content);
        ModelAndView modelaAndView =new ModelAndView("register");
        modelaAndView.addObject("map", map);
        
        return modelaAndView;
    }

    @RequestMapping("detail/{no}")
    public String detail(@PathVariable("no") int no){

        return"aaa";
    }

    /**
     * Get :  
     * 파라메터를 URL에 포함해서 요청
     * 데이터량에 제한 존재
     * charset이 없음 = 영어, 숫자, 문자만 사실상 가능
     * 
     * Post : 
     * 파라메터를 헤더에 포함해서 요청(URL에 노출되지 않음)
     * 데이터량 무제한
     * 헤더에 charset을 포함
     */
}
