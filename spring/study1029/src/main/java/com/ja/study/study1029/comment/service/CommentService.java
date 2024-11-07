package com.ja.study.study1029.comment.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.study.study1029.comment.dto.CommentDto;
import com.ja.study.study1029.comment.mapper.CommentSqlMapper;
import com.ja.study.study1029.user.dto.UserDto;
import com.ja.study.study1029.user.service.UserService;

@Service
public class CommentService {

    @Autowired
    private CommentSqlMapper commentSqlMapper;
    @Autowired
    private UserService userService;

    // 특정 게시물 댓글리스트
    public List<Map<String,Object>> getList(int id){
        List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
        List<CommentDto> commentDtoList = commentSqlMapper.commentFindByArticleId(id);

        for(CommentDto commentDto : commentDtoList){
            int userId = commentDto.getUserId();
            UserDto userDto = userService.getUserInfo(userId);
            Map<String, Object> map = new HashMap<>();
            System.out.println(userDto);
            map.put("commentDto", commentDto);
            map.put("userDto", userDto);

            resultList.add(map);
        }
        return resultList;
    }

    public void addComment(CommentDto commentDto){
        commentSqlMapper.addComment(commentDto);
    }

    public int commentsCount(int id){
        return commentSqlMapper.commentsCount(id);
    }
}
