package p6;

public class App {
    public static void main(String[] args) {
        // 제너릭
        AAA<String> a = new AAA<String>();
        String str = a.getValue();
        System.out.println(str);
        
        AAA<Integer> b = new AAA<Integer>();
        int dd = b.getValue();
        System.out.println(dd);

        BBB<Integer, String, Double> bbb = new BBB();
    }
}

// 인스턴스 생성시 타입이 정해짐 
// 클래스 정의를 할때 타입을 지정하고 싶지 않을 때 사용함
class AAA<T>{
    T value;
    public AAA(){

    }
    public T getValue(){
        return value;
    }

    public void test(){
        
    }
}

class BBB<T , E, Q>{
    T value1;
    E value2;
    Q value3;

    public T getT(){
        return value1;
    }
    public E getE(){
        return value2;
    }
}

