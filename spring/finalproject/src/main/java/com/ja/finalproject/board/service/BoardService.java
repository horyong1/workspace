package com.ja.finalproject.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.finalproject.board.mapper.BoardMapper;
import com.ja.finalproject.dto.articleDto;

@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;

    public List<articleDto> getBoardList(){
        return boardMapper.selectAllBoards();
    }

    public articleDto findByNoContent(int no){
        return boardMapper.selectFindByNoContent(no);
    }

    public void registerArticle(articleDto articleDto){
        boardMapper.createBoard(articleDto);
    }

    public void addReadCount(int no){
        boardMapper.addReadCount(no);
    }
}
