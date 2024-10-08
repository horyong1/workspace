package chat.server;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import chat.client.ClientDto;
import chat.client.Clients;

public class Server {
    public static void main(String[] args) throws Exception{
        System.out.println("chat Server start....");        
        System.out.println("chat Server loding....");
        
        ServerSocket serverSocket = new ServerSocket(9999);

        while (true) {
            Socket socket = serverSocket.accept();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String nickname = dis.readUTF();

            ClientDto clientDto = new ClientDto(nickname, socket);

            Clients.setList(clientDto);
            Clients.broadcast(nickname + "님이 입장하셨습니다.");

            new RecieveMessageForServer(clientDto).start();

        }



    }
}