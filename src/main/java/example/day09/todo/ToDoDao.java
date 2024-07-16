package example.day09.todo;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Component                  //  SPRING 컨테이너에 빈(객체) 등록
public class ToDoDao {

    private ToDoDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo1", "root", "1234");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

//    //get
//    public boolean toDoGet(String key){
//        try{
//            String sql = "insert into todolist (todo) values (?)";
//            ps = conn.prepareStatement(sql);
//            ps.setString(1,key);
//            int count = ps.executeUpdate();
//            if(count == 1){return true;}
//        }
//        catch (Exception e){System.out.println(e);} return false;
//    }
//
//    //post
//    public ArrayList<ToDoDto> toDoPost(){
//        ArrayList<ToDoDto> list = new ArrayList<>();
//
//        try{
//            String sql = "select * from todolist";
//            ps = conn.prepareStatement(sql);
//            rs= ps.executeQuery();
//            while (rs.next()){
//                String todo = rs.getString("todo");
//                int tNo = rs.getInt("tno");
//                int tState = rs.getInt("tstate");
//                ToDoDto todoDto = new ToDoDto();
//                todoDto.settContent(todo);
//                todoDto.settNo(tNo);
//                todoDto.settState(tState);
//                list.add(todoDto);
//            }
//        }
//        catch (Exception e){System.out.println(e);} return list;
//    }
//
//    //  3. HTTP PUT
//    public int toDoPut(ToDoDto toDoDto){
//        try{
//            if(toDoDto.gettState() == 0){
//                String sql = "update todoList set tstate = 1 where tno = ?";
//                ps = conn.prepareStatement(sql);
//                ps.setInt(1,toDoDto.gettNo());
//                int count = ps.executeUpdate();
//                toDoDto.settState(1);
//                if(count == 1){
//                    return 1;
//                }
//            }else if(toDoDto.gettState() == 1){
//                String sql = "update todoList set tstate = 0 where tno = ?";
//                ps = conn.prepareStatement(sql);
//                ps.setInt(1,toDoDto.gettNo());
//                int count = ps.executeUpdate();
//                toDoDto.settState(0);
//                if(count == 1){
//                    return 0;
//                }
//            }
//        }catch (Exception e){
//            System.out.println(e);
//        }
//        return -1;
//    }
//
//    //  4. HTTP DELETE
//    public boolean toDoDelete(int key){
//        try{
//            String sql = "delete from todoList where tno = ?";
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1,key);
//            int count = ps.executeUpdate();
//            if(count == 1){
//                return true;
//            }
//        }catch (Exception e){
//            System.out.println(e);
//        }
//        return false;
//    }

    //  1. 할 일 등록
    public boolean toDoCreate(String tContent){
        try{
            String sql = "insert into todolist(tcontent) values(?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, tContent);
            int count = ps.executeUpdate();
            if (count == 1) {
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    //  2. 할 일 전체 출력
    public ArrayList<ToDoDto> toDoReadAll(){
        ArrayList<ToDoDto> list = new ArrayList<>();
        try{
            String sql = "select * from todolist";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                ToDoDto toDoDto = new ToDoDto();

                toDoDto.setTState(rs.getInt("tstate"));
                toDoDto.setTNo(rs.getInt("tno"));
                toDoDto.setTContent(rs.getString("tcontent"));

                list.add(toDoDto);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }
    int tstate = 0;
    //  3. 할 일 상태 수정
    public boolean toDoUpdate(int tNo){
        try{
            String sql2 = "select *from todolist where tno = ?";
            ps = conn.prepareStatement(sql2);
            ps.setInt(1,tNo);
            rs = ps.executeQuery();
            if(rs.next()){
                tstate = rs.getInt("tstate");
                // 상태 변경 : 기본 상태가 0이면 1, 아니면 0
                tstate = tstate == 0 ? 1 : 0;
            }
            String sql = "update todolist set tstate = ? where tno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,tstate);
            ps.setInt(2, tNo);
            int count = ps.executeUpdate();
            if (count == 1) {
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    //  4. 할 일 삭제
    public boolean toDoDelete(int tNo){
        try{
            String sql = "delete from todolist where tno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, tNo);
            int count = ps.executeUpdate();
            if (count == 1) {
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
}
