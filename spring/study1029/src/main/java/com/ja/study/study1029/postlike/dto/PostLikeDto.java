package com.ja.study.study1029.postlike.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PostLikeDto {
    private int id;
    private int articleId;
    private int userId;
    private int articleLike;
    private Date createdAt;
}
