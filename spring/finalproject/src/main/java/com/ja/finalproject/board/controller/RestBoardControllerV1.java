package com.ja.finalproject.board.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ja.finalproject.board.service.BoardService;

@RequestMapping("/api/v1/board")
@RestController
public class RestBoardControllerV1 {

    @Autowired
    private BoardService boardService;

    // 모든 rest api는 ResponseDto를 따로 만들어서 반환하는 것이 좋다.
    @GetMapping("{id}")
    public Map<String,Object> getBoard(@PathVariable("id")int id){

        return boardService.getArticle(id);
    }
}
