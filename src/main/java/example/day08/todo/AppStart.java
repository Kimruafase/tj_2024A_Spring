package example.day08.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication      // 내장 톰캣(웹 서버) 실행, restful 지원, 스프링 MVC
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);

//        //  1. 할 일 등록   할 내용 (주기) 등록 성공 여부 (받기)
//        ToDoController.getInstance().toDoCreate("파이썬공부");
//
//        //  2. 할 일 전체 출력    전체 리스트 (받기)
//        ArrayList<ToDoDto> result = ToDoController.getInstance().toDoReadAll();
//        System.out.println(result);
//
//        //  3. 할 일 (상태) 출력     수정할 번호(주기) 수정 성공 여부(받기)
//        ToDoController.getInstance().toDoUpdate(5);
//        //  4. 할 일 삭제       삭제할 번호(주기) 삭제 성공 여부(받기)
//        ToDoController.getInstance().toDoDelete(13);
    }
}
