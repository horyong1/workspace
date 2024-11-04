package com.ja.study.study1029.comment.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CommentDto {
    private int id;
    private int articleId;
    private int userId;
    private String nickname;
    private String comment;
    private Date createdAt;
}
