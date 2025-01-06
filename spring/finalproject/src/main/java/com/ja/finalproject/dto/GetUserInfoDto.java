package com.ja.finalproject.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class GetUserInfoDto {
    private int id;
    private String nickname;
    private List<GetArticleDto> articleList = new ArrayList<>();
}
