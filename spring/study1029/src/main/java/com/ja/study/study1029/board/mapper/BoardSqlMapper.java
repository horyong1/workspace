package com.ja.study.study1029.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ja.study.study1029.board.dto.BoardDto;

import java.util.List;

@Mapper
public interface BoardSqlMapper {

    List<BoardDto> findAll();
    BoardDto findById(int id);
}
