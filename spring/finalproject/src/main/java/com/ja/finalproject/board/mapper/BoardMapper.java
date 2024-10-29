package com.ja.finalproject.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ja.finalproject.dto.articleDto;

@Mapper
public interface BoardMapper {
    void createBoard(articleDto articleDto);
    List<articleDto> selectAllBoards();
    articleDto selectFindByNoContent(int no);
    void addReadCount(int no);
    
}
