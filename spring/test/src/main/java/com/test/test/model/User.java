package com.test.test.model;

import java.util.Date;

import lombok.Data;

@Data
public class User {
    private String id;
    private String name;
    private String password;
    private String email;
    private String create_date;
}
