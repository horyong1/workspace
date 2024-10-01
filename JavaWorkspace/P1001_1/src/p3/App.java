package p3;

import java.util.*;

public class App {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.add("1번 작업");
        queue.add("2번 작업");
        queue.add("3번 작업");
        queue.add("4번 작업");

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.size());

        Stack<String> stack = new Stack<>();
        stack.push("1번작업");
        stack.push("2번작업");
        stack.push("3번작업");
        stack.push("4번작업");
        stack.push("5번작업");
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println(stack.toString());
    

        
    }



}
