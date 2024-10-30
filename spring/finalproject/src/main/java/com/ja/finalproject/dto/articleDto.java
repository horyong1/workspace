package com.ja.finalproject.dto;

import java.util.Date;

import lombok.Data;

@Data
public class articleDto {
    private int id;
    private int userId;
    private String title;
    private String content;
    private int readCount;
    private Date createdAt;
}
