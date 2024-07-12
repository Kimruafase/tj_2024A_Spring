package example.day07.todo;

import example.day02.consolemvc.model.dao.PhoneDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ToDoDao {

    private static ToDoDao toDoDao = new ToDoDao();
    private ToDoDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root", "1234");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static ToDoDao getInstance(){
        return toDoDao;
    }
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    //  3. HTTP PUT
    public int toDoPut(ToDoDto toDoDto){
        try{
            if(toDoDto.gettState() == 0){
                String sql = "update todoList set tstate = 1 where tno = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1,toDoDto.gettNo());
                int count = ps.executeUpdate();
                toDoDto.settState(1);
                System.out.println(toDoDto.gettState());
                if(count == 1){
                    return 1;
                }
            }else if(toDoDto.gettState() == 1){
                String sql = "update todoList set tstate = 0 where tno = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1,toDoDto.gettNo());
                int count = ps.executeUpdate();
                toDoDto.settState(0);
                if(count == 1){
                    return 0;
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return -1;
    }

    //  4. HTTP DELETE
    public boolean toDoDelete(int key){
        try{
            String sql = "delete from todoList where tno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,key);
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
}
