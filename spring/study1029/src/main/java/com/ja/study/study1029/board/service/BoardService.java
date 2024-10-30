package com.ja.study.study1029.board.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.study.study1029.board.dto.BoardDto;
import com.ja.study.study1029.board.mapper.BoardSqlMapper;
import com.ja.study.study1029.user.dto.UserDto;
import com.ja.study.study1029.user.mapper.UserSqlMapper;




@Service
public class BoardService {

    @Autowired
    private BoardSqlMapper boardSqlMapper;
    @Autowired
    private UserSqlMapper userSqlMapper;


    // 게시글 전체 목록
    public List<Map<String,Object>> findAll(){
        List<BoardDto> boardDtoList = boardSqlMapper.findAll();
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();

        for(BoardDto boardDto : boardDtoList){
            int userId = boardDto.getUserId();
            UserDto userDto = userSqlMapper.findById(userId);

            Map<String, Object> map = new HashMap<>();
            map.put("boardDto", boardDto);
            map.put("userDto", userDto);

            result.add(map);
        }
        
        return result;
    }

    // 상세 페이지 
    public Map<String, Object> getFindById(int id){
        Map<String, Object> result = new HashMap<>();
        BoardDto boardDto = boardSqlMapper.findById(id);
        int userId = boardDto.getUserId();
        UserDto userDto = userSqlMapper.findById(userId);
        result.put("boardDto", boardDto);
        result.put("userDto", userDto);
        return result;
    }

    // 조회수 증가
    public void addReadCount(int id){
        boardSqlMapper.addReadCount(id);
    }

    public void registerArticle(BoardDto boardDto){
        boardSqlMapper.addArticle(boardDto);
    }
}
