package example.day09.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;

@Service                    // 서비스 역할, SPRING 컨테이너에 빈(객체) 등록
public class ToDoService {

    @Autowired ToDoDao toDoDao;

    //  1. 할 일 등록
    public boolean toDoCreate(String tContent){
        System.out.println(toDoDao);
        boolean result = toDoDao.toDoCreate(tContent);
        return result;
    }

    //  2. 할 일 전체 출력
    public ArrayList<ToDoDto> toDoReadAll(){
        ArrayList<ToDoDto> result = toDoDao.toDoReadAll();
        return result;
    }

    //  3. 할 일 상태 수정
    public boolean toDoUpdate(int tNo){
        boolean result = toDoDao.toDoUpdate(tNo);
        return result;
    }

    //  4. 할 일 삭제
    public boolean toDoDelete(int tNo){
        boolean result = toDoDao.toDoDelete(tNo);
        return result;
    }
}
