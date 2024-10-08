package p2;

import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception{
        System.out.println("클라이언트 시작");

        Socket socket = new Socket("172.30.1.66",7777);
        socket.getOutputStream();
        
        System.out.println("클라이언트 접속 성공");

        socket.close();

    }
}
