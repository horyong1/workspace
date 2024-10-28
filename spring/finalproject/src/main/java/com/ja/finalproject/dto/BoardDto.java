package com.ja.finalproject.dto;

import lombok.Data;

@Data
public class BoardDto {
    private int id;
    private String userid;
    private String title;
    private String content;
    private int readcount;
    private String registerdate;
}
