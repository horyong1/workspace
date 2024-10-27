package com.test.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.test.test.model.Board;

@Mapper
public interface BoardMapper {

    void insertBoard(Board board);
    List<Board> selectAllBoards();
    Board findByNoBoard(int no);

}
