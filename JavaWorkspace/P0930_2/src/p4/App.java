package p4;

public class App {
    public static void main(String[] args) {
        AAA aaa = new AAA();

        try {
            int result = aaa.test(-10);
            System.out.println(result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class AAA{
    public int test(int a) throws Exception{
        if (a < 0){
            System.out.println("0보다 작으면 X");
            throw new Exception();    //checked 예외
        }
        return a;
    }

    public int test2(int a) throws Exception{
        if (a < 0){
            System.out.println("0보다 작으면 X");
            throw new Exception();    //checked 예외
        }
        return a;
    }

}

