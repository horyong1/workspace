package com.ja.finalproject.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserHobbyDto {
    private int id;
    private int userId;
    private int hobbyId;
    private Date createdAt;
}
