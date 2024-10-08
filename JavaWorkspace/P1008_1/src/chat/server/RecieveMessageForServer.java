package chat.server;

import java.io.DataInputStream;

import chat.client.ClientDto;
import chat.client.Clients;

public class RecieveMessageForServer extends Thread{
    private ClientDto clientDto;

    public RecieveMessageForServer(ClientDto clientDto){
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
