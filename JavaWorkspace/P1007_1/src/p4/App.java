package p4;


public class App {
    public static void main(String[] args) {
        //쓰레드 ==  비동기식
        AAA aaa = new AAA();
        BBB bbb = new BBB();

        // aaa.run();
        // bbb.run();

        aaa.start();
        bbb.start();
        System.out.println("@!@@@@@@@@@@@@@@@@@@@");
    }
}

class AAA extends Thread{
    @Override
    public void run(){
        for(int i = 1; i <= 1000; i++){
            System.out.println("야호 : " + i);
            try {
                Thread.sleep(100); // 0.5초간 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
}
class BBB extends Thread{

    @Override
    public void run(){
        for(int i = 1; i <= 1000; i++){
            System.out.println("안녕 : " + i);
            try {
                Thread.sleep(100); // 0.5초간 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
}