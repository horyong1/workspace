package p4;
import java.util.*;
public class App {
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>();

        set.add(5);
        set.add(7);
        set.add(3);
        set.add(6);
        set.add(1);
        set.add(8);
        set.add(9);
        set.add(0);
        set.add(2);
        set.add(5); // 값의 중복을 허용하지 않음 false 반환 

        System.out.println(set.contains(3));
        for(int a : set){
            System.out.println(a);
        }
    }
}
