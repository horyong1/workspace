package p6;

import java.util.concurrent.atomic.AtomicLong;

public class App {
    public static void main(String[] args) throws Exception{
        long time1 = System.currentTimeMillis();
        AAA aaa1 = new AAA(1, 2500000000L);
        AAA aaa2 = new AAA(2500000001L, 5000000000L);
        AAA aaa3 = new AAA(5000000001L, 7500000000L);
        AAA aaa4 = new AAA(7500000001L, 10000000000L);

        aaa1.start();
        aaa2.start();
        aaa3.start();
        aaa4.start();

        aaa1.join();
        aaa2.join();
        aaa3.join();
        aaa4.join();

        long  sum1 = aaa1.getSum() + aaa2.getSum() + aaa3.getSum() + aaa4.getSum();
        System.out.println(sum1);
        time1 = System.currentTimeMillis() - time1;
        System.out.println("실행시간 :::: " + time1);
        System.out.println("================");

        long time2 = System.currentTimeMillis();
        long sum = 0;
        for(long i = 1; i <= 10000000000L; i++){
            sum += i;
        }

        System.out.println(sum);
        time2 = System.currentTimeMillis() - time2;
        System.out.println("실행시간 :::: " + time2);
    }
}



class AAA extends Thread{
    public AAA(long start, long end){
        this.start = start;
        this.end = end;
    }
    private long start;
    private long end;
    private long sum = 0;
    


    @Override
    public synchronized void run(){
        for(long x = start; x <= end; x++){
            synchronized(this){
                sum += x;
            }
        }
    }


    public long getStart() {
        return start;
    }


    public long getEnd() {
        return end;
    }


    public long getSum() {
        return sum;
    }
}