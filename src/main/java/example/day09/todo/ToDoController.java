package example.day09.todo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController                     // 해당 클래스가 스프링 MVC 패턴에서 Controller 역할, SPRING 컨테이너(저장소)에 빈(객체) 등록
@RequestMapping("/todo2")
public class ToDoController {

    @Autowired ToDoService toDoService;

//    ToDoDto toDoDto = new ToDoDto();
//
//    //[1]
//    @GetMapping("/get")
//    public boolean toDoGet(HttpServletRequest request){
//        String key = request.getParameter("key");
//        return ToDoDao.getInstance().toDoGet(key);
//    }
//    //[2]
//    @PostMapping("/post")
//    public ArrayList<ToDoDto> toDoPost(HttpServletRequest request){
//
//        return ToDoDao.getInstance().toDoPost();
//    }
//    //  3. HTTP PUT
//    @PutMapping("/put")
//    public int toDoPut(HttpServletRequest request){
//        System.out.println("ToDoController.toDoPut");
//        int key = Integer.parseInt(request.getParameter("key"));
//        toDoDto.settNo(key);
//        return ToDoDao.getInstance().toDoPut(toDoDto);
//    }
//
//    //  4. HTTP DELETE
//    @DeleteMapping("/delete")
//    public boolean toDoDelete(HttpServletRequest request){
//        System.out.println("ToDoController.toDoDelete");
//        int key = Integer.parseInt(request.getParameter("key"));
//        return ToDoDao.getInstance().toDoDelete(key);
//    }

    //  싱글톤 패턴 : JVM 메소드 영역에 static 변수에 객체 주소 값 저장
//    private static ToDoController toDoController = new ToDoController();
//    private ToDoController() {
//
//    }
//    public static ToDoController getInstance(){
//        return toDoController;
//    }

    //  1. 할 일 등록
    @PostMapping("/post")
    public boolean toDoCreate(String tContent){
        System.out.println(toDoService);
        boolean result = toDoService.toDoCreate(tContent);
        return result;
    }

    //  2. 할 일 전체 출력
    @GetMapping("/get")
    public ArrayList<ToDoDto> toDoReadAll(){
        ArrayList<ToDoDto> result = toDoService.toDoReadAll();
        return result;
    }

    //  3. 할 일 상태 수정
    @PutMapping("/put")
    public boolean toDoUpdate(int tNo){
        boolean result = toDoService.toDoUpdate(tNo);
        return result;
    }

    //  4. 할 일 삭제
    @DeleteMapping("/delete")
    public boolean toDoDelete(int tNo){
        boolean result = toDoService.toDoDelete(tNo);
        return result;
    }
}
