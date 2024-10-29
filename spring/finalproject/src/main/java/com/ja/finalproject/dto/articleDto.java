package com.ja.finalproject.dto;

import java.util.Date;

import lombok.Data;

@Data
public class articleDto {
    private int id;
    private int userid;
    private String nickname;
    private String title;
    private String content;
    private int readcount;
    private Date createdAt;
    private String createdAtFormmat;
}
