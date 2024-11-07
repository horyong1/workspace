package com.ja.finalproject.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PostLikeDto {
    private int id;
    private int user_id;
    private int article_id;
    private int article_like;
    private Date created_at;
}
