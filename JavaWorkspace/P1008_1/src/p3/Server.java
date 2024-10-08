package p3;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        System.out.println("서버 시작");
        ServerSocket serverSocket = new ServerSocket(7777);
        System.out.println("서버 접속중 ..... ");
        Socket socket = serverSocket.accept();

        System.out.println(socket.getLocalAddress() + " 접속 완료");

        InputStream inputStream = socket.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        
        System.out.println(socket.getLocalAddress()+"부터 전달 값 대기중......");
        String inputValue = dataInputStream.readUTF();


        System.out.println("전달 받은 값 ::::: " + inputValue);
        System.out.println("접속 종료");

        serverSocket.close();

    }
}
