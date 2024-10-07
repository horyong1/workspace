package p5;

public class App {
    public static void main(String[] args) {
        // 쓰레드는 비동기식 프로그래밍 기법
        // 흐름을 새로 생성해서 독립적으로 따로 흘러가도록 코딩할 수 있다.
        // 하나의 메모리를 두개 이상의 쓰레드가 동시에 접근 할 수 있는 경우에는,
        // 버그가 발생할 가능성이 높다.
        Some1 s1 = new Some1();
        Some2 s2 = new Some2();

        s1.start();
        s2.start();

        

        System.out.println(Data.getSum());
    }
}

class Data{
    private static int sum = 0;

    public synchronized static void add(int value){
        System.out.println(String.format("%d + %d * %d = %d",sum,value,2,sum + value * 2 ));
        sum = sum + value * 2;
    }

    public static int getSum() {
        return sum;
    }
}
class Some1 extends Thread{
    public void run(){
        for(int i = 0; i < 1000; i++){
            Data.add(1);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
class Some2 extends Thread{
    public void run(){
        for(int i = 0; i < 1000; i++){
            Data.add(3);
        try{   
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }

        }
        
    }
}
