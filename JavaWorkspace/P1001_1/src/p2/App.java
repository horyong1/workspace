package p2;

import java.util.*;

public class App {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("한조1");
        list.add("한조2");
        list.add("한조3");
        list.add("한조4");
        list.add("한조5");
        list.add(3,"야호"); // 삽입은 하지말것 

        list.remove(3); 
        // LinkedList, ArrayList 삭제전 탐색 속도는 같음 
        // 삭제는 LinkedList가 삭제 할 주소값만 바꿔주기 때문에 빠름 
        list.remove("한조1"); 
        list.contains("한조1");
        System.out.println(list.toString());

        String str = list.get(0);
        // ArrayList는 배열구조기 때문에 반복문에는 
        // LinkedList보다 좋음
        // 자료구조에서는 이렇게 돌리면 안됨
        // for(int i = 0; i < list.size(); i++){
        //     System.out.println(list.get(i));
        // }
        // List 인터페이스 iterator메소드가 있음(반복자) 
        // 향상된 for문으로 List계열은 해야한다
        for(String e : list){
            System.out.println(e);
        }
    }
}
