package chat.client;

import java.io.DataInputStream;
import java.net.Socket;

public class RecieveMessage extends Thread{

    private Socket socket;

    public RecieveMessage(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try{
            
            DataInputStream dataInputStream = 
                    new DataInputStream(socket.getInputStream());
            while (true) {
                String message = dataInputStream.readUTF();
                System.out.println(message);
            }

        }catch(Exception e){
            System.out.println("채팅방 종료......");
        }
    }
}

