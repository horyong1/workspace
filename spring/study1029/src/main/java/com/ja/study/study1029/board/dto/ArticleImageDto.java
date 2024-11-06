package com.ja.study.study1029.board.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ArticleImageDto {
    private int id;
    private int articleid;
    private String location;
    private String originalFilename;
    private Date createdAt;

}
