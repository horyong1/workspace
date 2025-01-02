package com.ja.finalproject.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseArticleDto {
    private int id;
    private int userId;
    private String title;
    private String content;
    private int readCount;
    private Date createdAt;
}
