package p3;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class App {
    public static void main(String[] args) {
        try {
            File file = new File("C:/temp/aaa.bmp");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            DataOutputStream dos = new DataOutputStream(bos);
            
            // 헤더부분
            // 시그니처
            dos.write(0x42);
            dos.write(0x4D);
            
            // 총 파일의 크기 30054byte 역순
            dos.write(0x66);
            dos.write(0x75);
            dos.write(0x00);
            dos.write(0x00);
            
            // 예약
            dos.writeShort(0);
            dos.writeShort(0);
            
            // 오프셋 위치
            dos.write(0x36);
            dos.write(0x00);
            dos.write(0x00);
            dos.write(0x00);

            // 두번째 헤더 크기
            dos.write(0x28);
            dos.write(0x00);
            dos.write(0x00);
            dos.write(0x00);
            
            // 가로
            dos.write(0x64);
            dos.write(0x00);
            dos.write(0x00);
            dos.write(0x00);
            
            //세로
            dos.write(0x64);
            dos.write(0x00);
            dos.write(0x00);
            dos.write(0x00);
            
            // ...
            dos.write(0x01);
            dos.write(0x00);

            // 색수
            dos.write(0x18);
            dos.write(0x00);
            
            // 압축방식
            dos.write(0x00);
            dos.write(0x00);
            dos.write(0x00);
            dos.write(0x00);
            
            // 픽셀개수
            dos.writeInt(0);
            dos.writeInt(0);
            dos.writeInt(0);
            dos.writeInt(0);
            


            
            // 데이터 부분
            for(int x = 0; x < 100; x++){
                for(int y = 0; y < 100; y++){
                    if(y == 20 || x == 20){
                        dos.write(0xff);
                        dos.write(0x00);
                        dos.write(0x00);
                        
                    }else{
                        dos.write(0xff);
                        dos.write(0xff);
                        dos.write(0xff);

                    }
                }
            }
            dos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
