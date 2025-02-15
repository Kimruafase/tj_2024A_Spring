package example.day08.thread;

public class Timer implements Runnable{

    public int second;      // 타이머의 초를 저장하는 변수
    public boolean state = true;   // 타이머의 실행 상태
    @Override
    public void run() {
        while (state){
            this.second++;
            System.out.println(" >> " + this.second + "초 <<");
            try {
                Thread.sleep(1000);
            } catch (Exception e){
                System.out.println(e);
            }
        }   // while end

    }   // run() end

}   // class end
