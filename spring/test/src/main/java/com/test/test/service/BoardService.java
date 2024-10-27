package com.test.test.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.test.mapper.BoardMapper;
import com.test.test.model.Board;


@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;

    public void addBoard(Board board) {
        boardMapper.insertBoard(board);
    }

    public List<Board> getAllBoards(){
        return boardMapper.selectAllBoards();
    }
   
    public Board getByNoBoard(int no){
        return boardMapper.findByNoBoard(no);
    }



}
