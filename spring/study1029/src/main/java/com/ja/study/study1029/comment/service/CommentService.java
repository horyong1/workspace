package com.ja.study.study1029.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ja.study.study1029.comment.dto.CommentDto;
import com.ja.study.study1029.comment.mapper.CommentSqlMapper;

@Service
public class CommentService {

    @Autowired
    private CommentSqlMapper commentSqlMapper;

    // 특정 게시물 댓글리스트
    public List<CommentDto> getList(int id){
        return commentSqlMapper.commentFindByArticleId(id);
    }

    public void addComment(CommentDto commentDto){
        commentSqlMapper.addComment(commentDto);
    }

    public int commentsCount(int id){
        return commentSqlMapper.commentsCount(id);
    }
}
