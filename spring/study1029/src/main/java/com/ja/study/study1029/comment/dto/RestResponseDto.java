package com.ja.study.study1029.comment.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class RestResponseDto {
    private String result;
    private String resume;
    private Map<String,Object> map = new HashMap<>();

    public void add(String key, Object object){
        map.put(key, object);
    }
}
