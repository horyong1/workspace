package com.ja.finalproject.dto;

import java.util.Date;

import lombok.Data;

@Data
public class HobbyCategoryDto {
    private int id;
    private String hobbyName;
    private Date createdAt;
}
