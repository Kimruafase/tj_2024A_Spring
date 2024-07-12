package example.day07.todo;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
public class ToDoController {

    ToDoDto toDoDto = new ToDoDto();
    //  3. HTTP PUT
    @PutMapping("/put")
    public int toDoPut(HttpServletRequest request){
        System.out.println("ToDoController.toDoPut");
        int key = Integer.parseInt(request.getParameter("key"));
        toDoDto.settNo(key);
        return ToDoDao.getInstance().toDoPut(toDoDto);
    }

    //  4. HTTP DELETE
    @DeleteMapping("/delete")
    public boolean toDoDelete(HttpServletRequest request){
        System.out.println("ToDoController.toDoDelete");
        int key = Integer.parseInt(request.getParameter("key"));
        return ToDoDao.getInstance().toDoDelete(key);
    }
}
