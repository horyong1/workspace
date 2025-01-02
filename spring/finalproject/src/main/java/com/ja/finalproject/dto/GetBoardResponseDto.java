package com.ja.finalproject.dto;

import lombok.Data;

@Data
public class GetBoardResponseDto {
    private String title;
    private int id;
    private String content;
    private String user_id;
    private String nickname;
    private int read_count;
    private String write_date;
}
