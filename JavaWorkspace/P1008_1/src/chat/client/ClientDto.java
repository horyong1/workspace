package chat.client;

import java.net.Socket;

public class ClientDto {
    private String nickname;
    private Socket socket;
    
    public ClientDto(String nickname, Socket socket){
        this.nickname = nickname;
        this.socket = socket;
    }

    public String getNickname() {
        return nickname;
    }
    public Socket getSocket() {
        return socket;
    }

    
}
