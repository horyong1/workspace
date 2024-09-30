package p10;

public class App {
    public static void main(String[] args) {
        // 해시 알고리즘
        // y = f(x) 만족해야 한다.
        // 복호화 불가능  = 단방향 알고리즘
        // 항상 특정 범위내의 값이 나와야한다. (예 256비트)
        // 충돌

        int data = 1982371;
        int y = data * 3 + 5;
        int x = (y - 5) / 3;
        
        System.out.println(y);
        System.out.println(x);
    }
}
