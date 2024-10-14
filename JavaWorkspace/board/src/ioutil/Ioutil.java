package ioutil;

import java.util.Scanner;

public class Ioutil {
    private static Scanner sc = new Scanner(System.in);

    public static void print(String message){
        System.err.println(message);
    }

    public static String input(String message){
        System.out.print(message);
        return sc.nextLine();
    }

    public static void pause(){
        System.out.println("계속하시려면 엔터를 입력 해 주세요....");
        sc.nextLine();
    }
}
