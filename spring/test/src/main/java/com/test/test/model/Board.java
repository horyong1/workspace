package com.test.test.model;

import java.util.Date;

import lombok.Data;

@Data
public class Board {
    private int no;
    private String title;
    private String content;
    private String id;
    private String create_date;
}
