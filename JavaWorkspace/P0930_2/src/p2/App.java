package p2;

public class App {
    public static void main(String[] args) {
        // 예외 처리
        // 오류 3가지 종류 : 구문 오류(컴파일오류), 런타임 오류(익셉션), 논리 오류(버그)
        
        int a = 10;
        int b = 2;
        int c = a / b;

        try {
            String str = null;
            int length = str.length();

            System.out.println(length);
            
        } catch (NullPointerException e) {
            e.printStackTrace();
            
        } catch(ArithmeticException e){
            e.printStackTrace();
        }
        finally{
            System.out.println("무조건 실행");
        }
        System.out.println(c);
        System.out.println("야호");

    }
}
