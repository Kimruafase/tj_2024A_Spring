package example.day09.board;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Component
public class BoardDao {

//    private static BoardDao boardDao = new BoardDao();
//    public static BoardDao getInstance(){
//        return boardDao;
//    }
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    private BoardDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Board", "root", "1234");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //  1. get
    public ArrayList<BoardDto> boardGet1(){
        ArrayList<BoardDto> list = new ArrayList<>();
        try{
            String sql = "select *from wholepage";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                BoardDto boardDto = new BoardDto();
                boardDto.setPno(rs.getInt("pno"));
                boardDto.setPtitle(rs.getString("ptitle"));
                boardDto.setPdate(rs.getString("pdate"));
                boardDto.setPview(rs.getInt("pview"));

                list.add(boardDto);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }
    public BoardDto boardGet2(int pno){
        try{
            String sql = "select *from wholepage where pno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,pno);
            rs = ps.executeQuery();
            if(rs.next()){
                BoardDto boardDto = new BoardDto();
                boardDto.setPno(rs.getInt("pno"));
                boardDto.setPtitle(rs.getString("ptitle"));
                boardDto.setPdate(rs.getString("pdate"));
                boardDto.setPview(rs.getInt("pview"));
                boardDto.setPdetail(rs.getString("pdetail"));

                return boardDto;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    //  조회수 증가 함수
    public boolean viewUpdate(int pno){
        try{
            String sql = "update wholepage set pview = pview + 1 where pno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,pno);
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
    //  2. post
    public boolean boardPost(String title, String detail, String pw){
        try{
            String sql = "insert wholepage(ptitle, pdetail, ppwd) values(?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,title);
            ps.setString(2,detail);
            ps.setString(3,pw);
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
    //  3. put
    public boolean boardPut(String title, String detail, String pw,int pno){
        try{
            String sql = "update wholepage set ptitle = ? , pdetail = ? where ppwd = ? and pno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,title);
            ps.setString(2,detail);
            ps.setString(3,pw);
            ps.setInt(4,pno);
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    //  4. delete
    public boolean boardDelete(String pw, int pno){
        try{
            String sql = "delete from wholepage where pno = ? and ppwd = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,pno);
            ps.setString(2,pw);
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
