package com.ja.finalproject.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.finalproject.board.mapper.BoardMapper;
import com.ja.finalproject.dto.UserDto;
import com.ja.finalproject.dto.articleDto;
import com.ja.finalproject.user.mapper.UserSqlMapper;

@Service
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private UserSqlMapper userSqlMapper;

    // 글 쓰기
    public void registerArticle(articleDto articleDto){
        boardMapper.createBoard(articleDto);
    }

    // 게시판 전체 목록 가져오기
    public List<Map<String,Object>> getArticleList(){
        List<articleDto> articleDtoList = boardMapper.findAll();
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();

        for(articleDto articleDto : articleDtoList ){
            int userPk = articleDto.getUserId();
            UserDto userDto = userSqlMapper.findById(userPk);
            
            Map<String,Object> map = new HashMap<>();
            map.put("articleDto", articleDto);
            map.put("userDto", userDto);
            result.add(map);
        }
        return result;
    }

    // 번호로 게시판 글 상세 목록 가져오기
    public Map<String,Object> getArticle(int id){
        articleDto articleDto = boardMapper.selectFindByNoContent(id);
        int userPk = articleDto.getUserId();
        UserDto userDto = userSqlMapper.findById(userPk);

        Map<String,Object> map = new HashMap<>();
        map.put("articleDto", articleDto);
        map.put("userDto", userDto);
        
        return map;
    }

    // 조회수 증가
    public void addReadCount(int no){
        boardMapper.addReadCount(no);
    }

}
