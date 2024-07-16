package example.day09.thread;

public class Step2 {

    public static void main(String[] args) {

        //  1. Calculator 객체 생성
        Calculator mycal = new Calculator();

        //  2. User1 객체 생성
        User1 user1 = new User1();
        user1.value = 100;
        user1.setName("User1 Thread");

        //  3. User1 필드에 Calculator 객체 대입
        user1.calculator = mycal;
        user1.start();

        //  4. User2 객체 생성
        User1 user2 = new User1();
        user2.value = 200;
        user2.setName("User2 Thread");

        //  5. User2 필드에 User1 필드와 동일한 Calculator 객체 대입
        user2.calculator = mycal;
        user2.start();


    }   //  main end
}   // class end
