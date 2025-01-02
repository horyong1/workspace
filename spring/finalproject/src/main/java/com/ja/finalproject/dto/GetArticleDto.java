package com.ja.finalproject.dto;

import java.util.Date;

import lombok.Data;

@Data
public class GetArticleDto {
    private String title;
    private String content;
    private int readCount;
    private Date createAt;
}
