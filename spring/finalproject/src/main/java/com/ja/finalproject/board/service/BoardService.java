package com.ja.finalproject.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.finalproject.board.mapper.BoardMapper;
import com.ja.finalproject.dto.BoardDto;

@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;

    public List<BoardDto> getBoardList(){
        return boardMapper.selectAllBoards();
    }

    public BoardDto findByNoContent(int no){
        return boardMapper.selectFindByNoContent(no);
    }
}
