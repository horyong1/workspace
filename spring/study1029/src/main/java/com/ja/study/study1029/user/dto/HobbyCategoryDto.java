package com.ja.study.study1029.user.dto;

import java.util.Date;

import lombok.Data;

@Data
public class HobbyCategoryDto {
    private int id;
    private int hobbyId;
    private String hobbyName;
    private Date createdAt;
}
