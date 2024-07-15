package example.day08.thread;

import java.awt.*;

public class Step1 {
    public static void main(String[] args) {
        // ===================== 싱글 스레드 ===================== //
        //  1. "띵" 소리 5회 출력
        Toolkit toolkit = Toolkit.getDefaultToolkit();  //  Toolkit : java.awt 자바의 UI(화면, 소리 등) 라이브러리

        for (int i = 1; i <= 5; i++) {      //  5회 반복
            toolkit.beep();     //  비프음 소리 출력
            //  비프음 출력 속도보다 for 문의 5회 반복 속도가 더 빠름
            //  for 문을 처리하는 흐름[스레드]를 잠시 일시 정지
            try{
                // Thread.sleep() : ms 만큼 스레드가 일시 정지 ms = 1/1000초
                Thread.sleep(1000);
            }catch (Exception e){
                System.out.println(e);
            }
        }

        //  2. "띵" 5회 console text 출력
        for (int i = 0; i < 5; i++) {
            System.out.println("띵");
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                System.out.println(e);
            }
        }
        //  ======================================================  //

        //  ==================== 멀티 스레드 1 ====================  //

        //  1. 작업_스레드_A 의 객체 생성
        작업_스레드_A threadA = new 작업_스레드_A();

        //  2. 작업_스레드_A 의 스레드 실행(run 메소드 실행)
        threadA.run();

        //  3. "띵" 5회 console text 출력
        for (int i = 0; i < 5; i++) {
            System.out.println("띵");
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                System.out.println(e);
            }
        }   // for end

        //  ==================== 멀티 스레드 2 ====================  //

        Runnable runnable1 = new 작업_스레드_B();

        Thread threadB = new Thread(runnable1);

        threadB.start();

        for (int i = 0; i < 5; i++) {
            System.out.println("띵");
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                System.out.println(e);
            }
        }   // for end

        //  ==================== 멀티 스레드 3 ====================  //
        //  익명 객체 / 구현체 : 이름이 없는 객체
            //  new 생성자(){익명 구현체 정의}
        //  1. 구현 객체 생성
        Thread threadC = new Thread(){
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {      //  5회 반복
                    toolkit.beep();     //  비프음 소리 출력
                    //  비프음 출력 속도보다 for 문의 5회 반복 속도가 더 빠름
                    //  for 문을 처리하는 흐름[스레드]를 잠시 일시 정지
                    try {
                        // Thread.sleep() : ms 만큼 스레드가 일시 정지 ms = 1/1000초
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }   // for end
            }   // run() end

        };  // Thread 생성자 end
        threadC.start();

        for (int i = 0; i < 5; i++) {
            System.out.println("띵");
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                System.out.println(e);
            }
        }   // for end

        //  ==================== 멀티 스레드 4 ====================  //
        Thread threadD = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {      //  5회 반복
                    toolkit.beep();     //  비프음 소리 출력
                    //  비프음 출력 속도보다 for 문의 5회 반복 속도가 더 빠름
                    //  for 문을 처리하는 흐름[스레드]를 잠시 일시 정지
                    try {
                        // Thread.sleep() : ms 만큼 스레드가 일시 정지 ms = 1/1000초
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }   // for end
            }   // run() end
        });     // Thread 생성자 end
        threadD.start();

        for (int i = 0; i < 5; i++) {
            System.out.println("띵");
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                System.out.println(e);
            }
        }   // for end
    }   // main end
}   // class end
