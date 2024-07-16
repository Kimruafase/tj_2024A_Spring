package example.day09.thread;

public class Step1 {
    //  메인 thread 제공받음
    public static void main(String[] args) {

        //  1. 현재 코드를 실행하는 thread의 이름 호출
        Thread thread = Thread.currentThread();
        System.out.println("해당 코드를 읽는 thread명 : " + thread.getName());

        //  2. 여러 개의 thread 를 만들어서 thread 이름 확인
        for (int i = 0; i < 5; i++) {

            Thread threadA = new Thread(){
                @Override
                public void run() {
                    //  System.out.println(i);  //  i는 main thread 에 있어서 thread 가 달라서 공유 불가능
                   Thread thread = Thread.currentThread();
                   thread.setName("내가 만든 작업 thread");   // thread 이름 정의 가능
                    System.out.println("해당 코드를 읽는 thread명 : " + thread.getName());
                }   //  run() end
            };  //  생산자 end

            threadA.start();

        }   //  for end

        //  3. 현재 thread 를 주어진 시간 동안 일시 정지
        try {
            System.out.println(" ==== 5초 대기 중 ==== ");
            Thread.sleep(5000); //  thread.sleep(ms);   -> ms = 1/1000초
        } catch (Exception e){
            System.out.println(e);
        }
        System.out.println(" ==== 5초 후 ==== ");

        //  4. 서로 다른 thread 의 종료를 기다림
        SumThread sumThread = new SumThread();
        sumThread.start();
            //  main thread 가 SumThread 의 누적 합계를 구하기 전에 결과를 출력해버림
        System.out.println(" JOIN 전 합계 결과 : " + sumThread.sum);

            //  main thread 가 SumThread 가 종료될 때까지 기다리게 함
        try{
            sumThread.join();       //  main thread 와 SumThread 가 조인(흐름 합치기)
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println(" JOIN 후 합계 결과 : " + sumThread.sum);

        //  5. 다른 thread 에게 순서를 양보
        WorkThread workThreadA = new WorkThread();      //  thread 객체 생성
        workThreadA.setName("작업 스레드 A");            //  thread 이름 정의

        WorkThread workThreadB = new WorkThread();
        workThreadB.setName("작업 스레드 B");

        workThreadA.start();                        //  각 스레드 실행
        workThreadB.start();

        try {
            Thread.sleep(5000);             //  main thread 5초 정지
        }catch (Exception e){
            System.out.println(e);
        }
        workThreadA.work = false;               //  작업 스레드 A의 필드값 변경

        try{
            Thread.sleep(5000);             //  main thread 5초 정지
        }catch (Exception e){
            System.out.println(e);
        }
        workThreadA.work = true;                //  작업 스레드 A의 필드값 변경
    }   //  main end
}   // class end
