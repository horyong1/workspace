package ioutil;

import java.util.Scanner;

public class Ioutil {
    private static Scanner sc = new Scanner(System.in);

    public static void print(String message){
        System.out.println(message);
    }

    public static String input(String message){
        System.out.print(message);
        String input = sc.nextLine();
        return input;
    }

    public static void pause(){
        System.out.println("계속하시려면 엔터를 입력하세요.....");
        sc.nextLine();
    }
}
