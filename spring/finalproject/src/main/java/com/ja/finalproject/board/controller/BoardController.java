package com.ja.finalproject.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
        return"/board/boardListPage";
    }

    @RequestMapping("detailPage/{id}")
    public String getBoardDetail(@PathVariable("id") int no, Model model){
        BoardDto dto = boardService.findByNoContent(no);
        model.addAttribute("dto", dto);
        System.out.println("닉네임 >>> "+ dto.getNickname());
        return"/board/boardDetailPage";
    }
}
