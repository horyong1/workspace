package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        
        System.out.println("chat Client 시작");
        System.out.print("사용할 닉네임 >>>> ");
        String nickname = sc.nextLine();

        System.out.println("서버에 접속 중......");
        Socket socket = new Socket("172.30.1.35", 9999);

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        dos.writeUTF(nickname);
        
        new RecieveMessage(socket).start();
        while (true) {
            System.out.print("보낼 메세지 (나가기 : x) >>>> ");
            String message = sc.nextLine();

            if(message.equals("x") || message.equals("X")){
                break;
            }
            
            dos.writeUTF(message);
            
        }
        sc.close();
        socket.close();
        System.out.println("chat 서버 접속 종료......");
        
    }
}


class RecieveMessage extends Thread{

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
