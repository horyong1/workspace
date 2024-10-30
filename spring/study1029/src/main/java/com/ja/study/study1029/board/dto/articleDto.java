package com.ja.study.study1029.board.dto;

import lombok.Data;
import java.util.Date;

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
