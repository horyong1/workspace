package com.ja.test12.service;

import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService{

    @Override
    public void someMethod() {
        System.out.println("인스턴스 생성되서 메소드ㅓ 실행된다");

        
    }

    
}
