package example.day08.thread;

import java.awt.*;

public class 작업_스레드_A extends Thread{
    //  extends : 상속

    //  멀티 스레드 실행 코드 정의
        //  run() : 멀티 스레드 처리할 코드 정의
    @Override
    public void run() {
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
        }   // for end
    }   // run() end
}   // class end
