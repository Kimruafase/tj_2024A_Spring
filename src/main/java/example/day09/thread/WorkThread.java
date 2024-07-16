package example.day09.thread;

public class WorkThread extends Thread{

    public boolean work = true;

    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                System.out.println(e);
            }   //  try, catch end

            if(work) {
                System.out.println(Thread.currentThread().getName());
            }else{
                Thread.yield();     // 다른 thread 에게 순서를 양보
            }

        }   //  while end


    }   //  run() end


}   // class end
