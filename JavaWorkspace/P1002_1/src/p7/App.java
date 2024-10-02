package p7;

public class App {
    public static void main(String[] args) {

        long time = System.currentTimeMillis();
        String str = "";
        for(int i = 0; i < 1000; i++){
            str += "안녕하세요";
        }
        System.out.println(str);
        time = System.currentTimeMillis() - time;
        System.out.println("총 로직시간 :::: " + time);        

        int x = 3;
        int y = 7;

        // String result = x + " + " + y + " = " + x * y;
        String result = String.format("%d 곱하기 %d = %d 입니다. ",x,y,x*y );
        System.out.println(result);
    }
}
