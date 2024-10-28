package com.ja.finalproject.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ja.finalproject.dto.BoardDto;

@Mapper
public interface BoardMapper {
    List<BoardDto> selectAllBoards();
    BoardDto selectFindByNoContent(int no);
    
}
