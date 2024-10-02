package p6;

import java.util.*;
import java.util.function.Function;

public class App {
    public static void main(String[] args) {
        Function<Integer, String> f = e -> e + "dd";
        System.out.println(f.apply(5));

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        list.stream()
            .map(e -> e + 3)
            .forEachOrdered(System.out::println)
        ;

        list.stream()
            // .map(e -> e + 3)
            // .allMatch(e -> e < 10)
            .collect(Math::random, 
            (a,b) -> System.out.println(a+b),
            (a,b) -> System.out.println(a+b))  //Supplier 값을 안 받고 리턴함
            
        ;
    }
}




class Test1 implements Function<Integer, String>{

    @Override
    public String apply(Integer t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'apply'");
    }
    
}