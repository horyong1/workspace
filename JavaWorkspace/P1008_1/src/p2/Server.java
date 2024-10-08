package p2;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        System.out.println("서버 시작");
        ServerSocket serverSocket = new ServerSocket(7777);

        System.out.println("접속 대기중 ...... ");

        Socket socket = serverSocket.accept();

        System.out.println("접속 완료 .......");
        
        socket.close();
        serverSocket.close();

    }
}
