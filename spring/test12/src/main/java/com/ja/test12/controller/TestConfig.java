package com.ja.test12.controller;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 어떠한 설정 파일이다 알려주는 어노테이션
@Configuration
public class TestConfig {

    @Bean
    public Scanner qwer(){
        return new Scanner(System.in);
    }
}
