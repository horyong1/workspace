package chat;

import java.io.DataInputStream;

public class RecieveMessage extends Thread{
    private ClientDto clientDto;

    public RecieveMessage(ClientDto clientDto){
        this.clientDto = clientDto;
    }

    @Override
    public void run(){
        try {

            DataInputStream dis =
                 new DataInputStream(clientDto.getSocket().getInputStream());
            while (true) {
                String message = dis.readUTF();
                Clients.broadcast(clientDto.getNickname() +" : " +message);
                
                
                
            }
        } catch (Exception e) {

        }
    }
}
