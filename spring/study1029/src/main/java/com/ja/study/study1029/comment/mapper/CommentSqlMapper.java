package com.ja.study.study1029.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ja.study.study1029.comment.dto.CommentDto;

@Mapper
public interface CommentSqlMapper {
    List<CommentDto> commentFindByArticleId(int id);
    void addComment(CommentDto comment);
    int commentsCount(int id);
}
