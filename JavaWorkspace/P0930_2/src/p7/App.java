package p7;

import java.util.ArrayList;
import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        ArrayList<StudentDto> list = new ArrayList<>();
        list.add(new StudentDto());
        ArrayList<String> names = new ArrayList<>();    
        names.add("ㅎㅇ");
        System.out.println(names.get(0));
        
        HashMap<Integer,String> map = new HashMap<>();
        map.put(1, "1번");
        System.out.println(map.get(1));
    }

}

class StudentDto{
    String name;
    int age;
    int score;

}

class MyArrayList<E>{
    E[] arr;
    int count;
    public void add(E a){
        arr[count++] = a;
    }

}