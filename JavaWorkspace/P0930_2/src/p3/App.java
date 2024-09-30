package p3;

public class App {
    public static void main(String[] args) {
        new AAA().test(5);
 }
}

class AAA{
    public void test(int a){
        int v = 10;
        try {
            int result = v / a;
            System.out.println("결과 : " + result);

            if (result < 5) {
                return;
            }

        } catch (ArithmeticException e) {
            System.out.println("익셉션 발생");

        } finally{
            System.out.println("무조건 실행");
        }

        System.out.println("test메소드 끝!!!");

    }
}