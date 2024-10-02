package p2;

public class App {
    public static void main(String[] args) {
        // 람다식 = 간결한 코드 = 읽기 좋은 코드 = 함수형 프로그래밍
        // 프론트쪽에선 이벤트 기능때문에 람다식을 많이 사용함(콜백함수)
        // 백엔드쪽에선 이벤트 기능이 거의 없기 때문에 잘 사용하지않음
        QWER qwer = new AAA();
        qwer.test();

        // 익명클래스 선언과 동시에 인스턴스 생성을 함 1회용 
        qwer = new QWER(){
            public void test(){
                System.out.println("익명클래스");
            }
            
        };
        qwer.test();
        qwer.test();

        // 람다식 익명클래스처럼 사용 함.
        qwer = () -> System.out.println("람다식");
        qwer.test();
        qwer.test();
        qwer.test();
        
        // 고차함수
        Some s = new Some();
        s.test(10,20,() -> System.out.println("람다식!!"));

    }   
}

interface QWER{
    public void test();
}

class AAA implements QWER{

    @Override
    public void test() {
        System.out.println("AAAA");
    }

}

interface Yaho{
    public void foo();
}

class Some{

    // 고차함수 = 콜백을 요구하는 함수
    // 자바에선 인터페이스를 이용하여 사용 해야 함
    public int test(int a, int b, Yaho func){
        func.foo();
        int result = a + b;
        return result;
    }
}