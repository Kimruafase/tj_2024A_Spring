package example.day09.thread;

public class Calculator {
    int memory;

    public synchronized void setMemory(int memory){
//    public void setMemory(int memory){
        System.out.println("memory = " + memory);
        //  매개변수 값을 필드에 저장
        this.memory = memory;

        //  2초간 일시 정지
        try{
            Thread.sleep(2000);
        }catch (Exception e){
            System.out.println(e);
        }

        //  현재 실행하고 있는 thread 의 이름 출력
        System.out.println(Thread.currentThread().getName());

        //  현재 필드값을 확인
        System.out.println(this.memory);
    }
}
