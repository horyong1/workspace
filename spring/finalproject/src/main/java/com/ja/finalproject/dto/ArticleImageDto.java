package com.ja.finalproject.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ArticleImageDto {
    private int id;
    private int articleId;
    private String location;
    private String originalFilename;
    private Date createdAt;
}
