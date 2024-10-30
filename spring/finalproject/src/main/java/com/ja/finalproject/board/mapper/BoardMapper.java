package com.ja.finalproject.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ja.finalproject.dto.articleDto;

@Mapper
public interface BoardMapper {
    void createBoard(articleDto articleDto);
    List<articleDto> findAll();
    articleDto selectFindByNoContent(int id);
    void addReadCount(int no);
    
}
