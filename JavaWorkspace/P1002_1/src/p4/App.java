package p4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * 람다식 = > 함수형 프로그래밍 기법
 * 함수형 프로그래밍 기법은 배웠다고 해서 그냥 되는건 아니고
 * 프레임워크에서 함수형 프로그래밍 기법을 제공해줘야됨 예) React
 * Java에서 함수형 프로그래밍 기법을 제공하는 라이브러리 예) Stream API
 */
public class App {
    public static void main(String[] args) {
        
        List<String> nameList = new ArrayList<>();
        nameList.add("한조1");
        nameList.add("한조2");
        nameList.add("한조3");
        nameList.add("한조4");
        nameList.add("한조5");
        
        // Iterator 이용해서 출력하기
        Iterator<String> iterator = nameList.iterator();
        while (iterator.hasNext()) {
            String result = iterator.next();
            System.out.println(result);
        }
        System.out.println("=========");
        // 고전 for문으로 자료구조를 반복 돌리는 일은 특수한 상황 아니면 없어야 한다.
        // 99% for-each 문으로 함
        for(String s : nameList){
            System.out.println(s);
        }
        // stream API
        System.out.println("=====stream API=======");
        AAA a = new AAA();
        nameList.forEach(System.out::println);
        
        // 함수형 프로그래밍 기법으로 해결 
        System.out.println("=====s=======");

        List<Integer> scoreList = new ArrayList<>();
        scoreList.add(76);
        scoreList.add(3);
        scoreList.add(77);
        scoreList.add(56);
        scoreList.add(88);
        scoreList.add(97);
        scoreList.add(97);
        scoreList.add(100);
        scoreList.add(55);

        for(int e : scoreList){
            if(e % 2 == 0){
                continue;
            }
            System.out.println(e);
        }

        System.out.println("=====함수형 프로그램밍 기법=======");
        BBB b = new BBB();

        // 함수형 프로그래밍 기법
        scoreList.stream()
                // .filter(b)
                .distinct()
                .filter(e -> e % 2 !=0)
                .forEach(System.out::println)
        ;

        int sum = 
            IntStream.range(1, 10)
            .filter(e -> e % 2 == 0)
            .sum();
        
    }
}


class AAA implements Consumer<String>{

    @Override
    public void accept(String t) {
        System.out.println(t);
    }

}

class BBB implements Predicate<Integer>{

    @Override
    public boolean test(Integer t) {
        return t % 2 == 0 ? true:false;
    }

}