package p1;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class App {
    public static void main(String[] args) {
        int a = 10;

        File file = new File("C:/tepm/aaa.dat");
        try{
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            DataOutputStream dos = new DataOutputStream(bos);
            // fileOutputStream.write(a >> 24);
            // fileOutputStream.write(a >> 16);
            // fileOutputStream.write(a >> 8);
            // fileOutputStream.write(a >> 0);
            dos.writeInt(a);
            dos.writeInt(a);

            dos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        

    }
}

