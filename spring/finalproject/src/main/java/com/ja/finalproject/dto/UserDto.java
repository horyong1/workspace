package com.ja.finalproject.dto;

import lombok.Data;

@Data
public class UserDto {
    private int id;
    private String email;
    private String password;
    private String nickname;
    private String registerdate;
}
