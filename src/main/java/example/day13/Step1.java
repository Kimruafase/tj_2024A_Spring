package example.day13;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Step1 {
    public static void main(String[] args) {


        //  1. 스택
        Stack<Integer> coinBox = new Stack<>();

        //  2. 스택에 Push
        coinBox.push(100);
        coinBox.push(50);
        coinBox.push(500);
        coinBox.push(70);
        System.out.println("coinBox = " + coinBox);
        
        //  4. 스택에 PEEK
        int topData = coinBox.peek();
        System.out.println("topData = " + topData);

        //  3. 스택에 POP
        coinBox.pop();
        System.out.println("coinBox = " + coinBox);
        coinBox.pop();
        System.out.println("coinBox = " + coinBox);
        coinBox.pop();
        System.out.println("coinBox = " + coinBox);
        coinBox.pop();
        System.out.println("coinBox = " + coinBox);

        //  5. 큐
        Queue<Integer> pointBox = new LinkedList<>();

        //  6. 큐에 Enqueue -> offer
        pointBox.offer(100);
        pointBox.offer(50);
        pointBox.offer(500);
        pointBox.offer(70);
        System.out.println("pointBox = " + pointBox);

        //  7. 큐에 Peek
        int frontData = pointBox.peek();
        System.out.println("frontData = " + frontData);
        
        //  8. 큐에 Dequeue -> poll
        pointBox.poll();
        System.out.println("pointBox = " + pointBox);
        pointBox.poll();
        System.out.println("pointBox = " + pointBox);
        pointBox.poll();
        System.out.println("pointBox = " + pointBox);
        pointBox.poll();
        System.out.println("pointBox = " + pointBox);


    }
}
