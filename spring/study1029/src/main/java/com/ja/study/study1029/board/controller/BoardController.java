package com.ja.study.study1029.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.MapAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ja.study.study1029.board.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 글쓰기
    @RequestMapping("writePage")
    public String writePage(){
        return"board/writePage";
    }
    
    // 게시판 페이지
    @RequestMapping("mainPage")
    public String mainPage(Model model){   
        List<Map<String,Object>> boardlist = boardService.findAll();
        model.addAttribute("boardlist", boardlist);
        System.out.println("정보 >>>>>>>> " + boardlist);
        return "board/mainPage";
    }

    @RequestMapping("detailPage/{id}")
    public String detailPage(@PathVariable("id") int id, Model model){
        Map<String,Object> boardMap = boardService.getFindById(id);
        model.addAttribute("boardMap", boardMap);
        return "board/detailPage";
    }

}
