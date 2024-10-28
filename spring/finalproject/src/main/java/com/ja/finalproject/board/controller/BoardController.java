package com.ja.finalproject.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ja.finalproject.board.service.BoardService;
import com.ja.finalproject.dto.BoardDto;

@Controller
@RequestMapping("board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 게시판 목록
    @RequestMapping("list")
    public String getBoardList(Model model){
        List<BoardDto> list = boardService.getBoardList();
        model.addAttribute("list", list);
        for(BoardDto d : list){
            System.out.println(d);
        }
        return"boardList";
    }
}
