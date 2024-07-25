package web.model.dao;

import org.springframework.stereotype.Component;
import web.model.dto.BoardDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

@Component
public class BoardDao extends Dao {



    //글쓰기
    public boolean post(Map<String , String> map){
        try{
            String sql = "insert into board(btitle, bcontent, bcno,no) values(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,map.get("btitle"));
            ps.setString(2,map.get("bcontent"));
            ps.setString(3,map.get("bcno"));
            ps.setInt(4,Integer.parseInt(map.get("no")));

            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }


    //글 전체 호출
    public ArrayList<BoardDto> all(){
        ArrayList<BoardDto> list = new ArrayList<>();
        try {
            String sql="select *from board join member on board.no = member.no";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int bno = rs.getInt("bno");
                String btitle = rs.getString("btitle");
                String id = rs.getString("id");
                String bdate = rs.getString("bdate");
                long bview = rs.getLong("bview");

                BoardDto boardDto = new BoardDto();
                boardDto.setBno(bno);
                boardDto.setBtitle(btitle);
                boardDto.setId(id);
                boardDto.setBdate(bdate);
                boardDto.setBview(bview);
                list.add(boardDto);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }

    //글 상세 호출

    //글 수정

    //글 삭제

    //카테고리 호출
    public ArrayList<BoardDto> category(){
        ArrayList<BoardDto> list = new ArrayList<>();
        try {
            String sql = "select * from bcategory";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int bcno = rs.getInt("bcno");
                String bcname = rs.getString("bcname");

                BoardDto boardDto = new BoardDto();
                boardDto.setBcno(bcno);
                boardDto.setBcname(bcname);
                list.add(boardDto);
            }
        }catch (Exception e){ System.out.println(e);  }
        return list;
    }
}