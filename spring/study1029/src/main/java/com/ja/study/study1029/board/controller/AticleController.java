package com.ja.study.study1029.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ja.study.study1029.board.service.AticleService;

@Controller
@RequestMapping("board")
public class AticleController {

    @Autowired
    private AticleService aticleService;

    @RequestMapping("mainPage")
    public String mainPage(){
        return "board/mainPage";
    }
}
