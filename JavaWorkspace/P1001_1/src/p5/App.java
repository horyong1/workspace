package p5;

import java.util.*;

public class App {
    public static void main(String[] args) {
        // Map or Dto는 한끗차이
        // Map VS Dto 
        Map<String, Integer> map = new HashMap<>();
        map.put("짱구", 1);    
        map.put("짱아", 2);    
        map.put("철수", 3);    
        map.put("훈이", 4);    
        map.put("훈이", 5); // 키값이 존재 하면 값을 덮어 씌운다.

        int a = map.get("훈이");
        System.out.println(a);

        Map<Integer, StudentDto> map2 = new HashMap<>();
        
        map2.put("1", new StudentDto("ddd", 1, 1));
    }
}


class StudentDto{
    String name;
    int age;
    int score;
    
    public StudentDto(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    
}