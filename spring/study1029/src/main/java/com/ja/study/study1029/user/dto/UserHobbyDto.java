package com.ja.study.study1029.user.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserHobbyDto {
    private int id;
    private int userId;
    private int hobbyId;
    private Date createdAt;
}
