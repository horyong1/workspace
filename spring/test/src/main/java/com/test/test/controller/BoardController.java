package com.test.test.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.test.model.Board;
import com.test.test.service.BoardService;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @RequestMapping("/board")
    public String getBoard(Model model){
        List<Board> boards = boardService.getAllBoards();
        model.addAttribute("boards", boards);
        return "boardList";
    }

    @RequestMapping("/board/{no}")
    public String getNoBoard(@PathVariable("no") int no, Model model){
        Board board = boardService.getByNoBoard(no);
        System.out.println("============================");
        System.out.println("============================");
        System.out.println("============================");
        System.out.println(no);
        
        System.out.println("============================");
        System.out.println("============================");
        System.out.println("============================");
        model.addAttribute("board", board);
        return "boardDetail";
    }
}
