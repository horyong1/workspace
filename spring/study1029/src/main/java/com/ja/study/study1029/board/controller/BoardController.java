package com.ja.study.study1029.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ja.study.study1029.board.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 게시판 페이지
    @RequestMapping("mainPage")
    public String mainPage(Model model){   
        List<Map<String,Object>> list = boardService.findAll();
        model.addAttribute("list", list);
        System.out.println("정보 >>>>>>>> " + list);
        return "board/mainPage";
    }

    // 글쓰기
    @RequestMapping("writePage")
    public String writePage(){
        return"board/writePage";
    }
}
