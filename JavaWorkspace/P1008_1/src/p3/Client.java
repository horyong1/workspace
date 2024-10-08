package p3;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("클라이언트 시작");
        System.out.println("클라이언트 서버 접속중....");

        Socket socket = new Socket("172.30.1.66",7777);
        System.out.println("클라이언트 서버 접속 완료!");
        
        System.out.print("전달 값 작성 >>>>>  ");
        String value = sc.nextLine();
        sc.close();

        OutputStream OutputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(OutputStream);

        dataOutputStream.writeUTF(value);
        System.out.println("전달 값 전송 완료!!!");
        socket.close();
    }
}
