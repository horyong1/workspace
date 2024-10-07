package p2;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public class App {
    public static void main(String[] args) {
        //파일 읽기 (로딩)
        File file = new File("C:/tepm/aaa.dat");

        if(file.exists()){
            try {
               FileInputStream fis = new FileInputStream(file);
               BufferedInputStream bis = new BufferedInputStream(fis);
               DataInputStream dis = new DataInputStream(bis);
               
               //4byte 씩 read함  
            //    System.out.println(dis.read());
            //    System.out.println(dis.read());
            //    System.out.println(dis.read());
            //    System.out.println(dis.read());
            //    System.out.println(dis.read());
            //    System.out.println(dis.read());
            //    System.out.println(dis.read());
            //    System.out.println(dis.read());

               int a = dis.readInt();
               int b = dis.readInt();

               System.out.println(a);
               System.out.println(b);

               dis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
