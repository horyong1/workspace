package p7;

public class App {
    public static void main(String[] args) {
        // StringBuffer와 StringBuilder의 차이점에 대해서 서술하세요

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < sb.length(); i++){
            sb.append("안녕");
        }

        String str = sb.toString();


    }
}
