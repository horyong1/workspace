package p3;

/**
 * 람다식 문법
 * 문법적 필수 조건: 인터페이스에 단 한개의 추상 메서드가 존재해야한다.
 * 일반적 조건(선택) : 왠만하면 한줄짜리 코드여야 한다.
 */
public class App {
    public static void main(String[] args) {
        String str = "뉴비";
        int num = 1;
        
        // 람다식 기본 : (param1, param2, .... paramN) - > {로직들}
        // 람다식 코드 한줄에는 오버라이딩 + 인스턴스 생성까지 다 포함
        // 문법의도 : 변수에다가 함수를 담는 듯한 코드 구조
        // 일반적으로는 콜백 함수를 만드는 용도(내가 정의해서 호출 할 목적은 아님)
        // 메소드명 or 파라미터 타입 신경 안써도됨 단 한개의 메소드만 존재 하기 때문에
        AAA ref1 = (a, b) -> { 
            String result ="";
            System.out.println("시작@@@@@@@@@@@@@@@@");
            while (b<=10) {
                result = a + b++;
                System.out.println(result);
            }
            System.out.println("종료@@@@@@@@@@@@@@@@");
            };

        ref1.qwer1(str, num);


        // 람다식 단축 1
        // 조건 : 파라메터가 단 한개인 경우
        // 앞 소괄호를 빼고 작성 할 수 있다.(a) -> a
        BBB ref2 = a ->{ return a; };
        System.out.println("bbb ::: " + ref2.bbb(num));

        // 람다식 단축 2
        // 조건 : 로직이 단 한 줄인 경우
        // 대괄호를 생략 할 수 있다.
        AAA ref3 = (a,b) -> System.out.println(a+b);
        ref3.qwer1(str, num);

        // 람다식 단축 3
        // 조건 : 리턴 타입이 존재하면서 로직이 한줄인 경우
        // 대괄호를 생략하면서 return도 지울수있다(param이 1개라 소괄호 생략)
        // 이 코드를 보면 무조건 return 타입이 있다는걸 인지
        BBB ref4 = a -> 1 + a;
        
        // 람다식 단축 4 - 메서드 참조
        // 조건 : 로직이 한줄이여야 되고, 그 로직은 메서드 호출이어야 하고
        // 호출되는 메서드가 추상 메서드의 파라미터와 완전 동일한 경우 
        // 파라미터와 메서드에서 요구하는게 완전히 같다면 다 생략 가능함
        // 아래 코드와 같은 코드임
        // CCC ref5 = (a,b) -> Math.min(a, b);
        // 터널링
        CCC ref5 = Math::min;
        
        // 람다식 단축5 - 생성자 참조
        // 조건 : 메서드의 매개변수 타입과 개수, 반환타입이 생성자와 일치해야함
        DDD ref6 = FFF::new;
        FFF fff = ref6.create(12, 2);

    }
}

// 람다식 사용하려면 인터페이스 안에 메소드가 1개만 있어야 한다.
interface AAA{
    public void qwer1(String a, int b);
}

interface BBB{
    public int bbb(int a);
}

interface CCC{
    public void qwer3(int a, int b);
}
interface DDD{
    FFF create(int a, int b);
}
class FFF{
    int a;
    int b;

    FFF(int a, int b){
        this.a = a;
        this.b = b;
    }

    public int getA(){
        return a;
    }
}