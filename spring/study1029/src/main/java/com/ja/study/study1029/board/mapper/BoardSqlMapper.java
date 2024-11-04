package com.ja.study.study1029.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ja.study.study1029.board.dto.BoardDto;

@Mapper
public interface BoardSqlMapper {
    
    List<BoardDto> findAll();
    BoardDto findById(int id);
    void addReadCount(int id);
    void addArticle(BoardDto boardDto);
    void updateArticle(BoardDto boardto);
    void deleteArticle(BoardDto boardto);
    List<BoardDto> findByContent(@Param("select")String select, @Param("search")String search);
}
