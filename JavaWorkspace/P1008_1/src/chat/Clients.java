package chat;

import java.io.DataOutputStream;
import java.util.List;
import java.util.Vector;

public class Clients {
    // synchronized 기능이 있는  vector 사용 쓰레드 안정성을 위해
    private static final List<ClientDto> list = new Vector<>();

    public static void broadcast(String fullMessage){
        System.out.println("[ 서버 로그 ] " + fullMessage);

        for(ClientDto clientDto: list ){
            try{
                DataOutputStream dos = 
                    new DataOutputStream(clientDto.getSocket().getOutputStream());    
                
            }catch(Exception e){
                Clients.removeList(clientDto);
                Clients.broadcast(clientDto.getNickname() + "님이 퇴장하셨습니다.");
            }
        }
    }

    public static List<ClientDto> getList() {
        return list;
    }
    
    public static void setList(ClientDto clientDtos){
        list.add(clientDtos);
    }

    public static void removeList(ClientDto clientDto){
        list.remove(clientDto);
    }
}
