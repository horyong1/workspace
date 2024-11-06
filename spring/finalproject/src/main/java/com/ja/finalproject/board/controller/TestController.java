package com.ja.finalproject.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ja.finalproject.board.service.BoardService;

import jakarta.servlet.http.HttpSession;


// 여기서 사용되는 api 는 restAPI 임
@RestController
public class TestController {

    @Autowired
    private BoardService boardService;

    @RequestMapping("test1")
    public String test1(HttpSession session, Model model){
        String message = "출력해야할 메세지";
        // session.setAttribute("qwer", message);

        // request는 한번의 요청 후 메모리에서 소멸함 
        // request.setAttribute("qwer", message);

        model.addAttribute("qwer", message);
        return "test/test";
    }

    @RequestMapping("api/test1")
    public List<Map<String,Object>> test1(){
        return boardService.getArticleList(null, null, 1);
    }
}
