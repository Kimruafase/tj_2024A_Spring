package example.day07.todo;

import example.day02.consolemvc.model.dao.PhoneDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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

    //get
    public boolean toDoGet(String key){
        try{
            String sql = "insert into todolist (todo) values (?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,key);
            int count = ps.executeUpdate();
            if(count == 1){return true;}
        }
        catch (Exception e){System.out.println(e);} return false;
    }

    //post
    public ArrayList<ToDoDto> toDoPost(){
        ArrayList<ToDoDto> list = new ArrayList<>();

        try{
            String sql = "select * from todolist";
            ps = conn.prepareStatement(sql);
            rs= ps.executeQuery();
            while (rs.next()){
                String todo = rs.getString("todo");
                int tNo = rs.getInt("tno");
                int tState = rs.getInt("tstate");
                ToDoDto todoDto = new ToDoDto();
                todoDto.setToDo(todo);
                todoDto.settNo(tNo);
                todoDto.settState(tState);
                list.add(todoDto);
            }
        }
        catch (Exception e){System.out.println(e);} return list;
    }

    //  3. HTTP PUT
    public int toDoPut(ToDoDto toDoDto){
        try{
            if(toDoDto.gettState() == 0){
                String sql = "update todoList set tstate = 1 where tno = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1,toDoDto.gettNo());
                int count = ps.executeUpdate();
                toDoDto.settState(1);
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
