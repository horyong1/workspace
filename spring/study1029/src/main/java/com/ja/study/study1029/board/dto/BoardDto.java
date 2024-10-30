package com.ja.study.study1029.board.dto;

import lombok.Data;
import java.util.Date;

@Data
public class BoardDto {
    private int id;
    private int userId;
    private String title;
    private String content;
    private int readCount;
    private Date createdAt;
}
