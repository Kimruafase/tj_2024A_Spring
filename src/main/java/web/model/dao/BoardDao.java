package web.model.dao;

import org.springframework.stereotype.Component;
import web.model.dto.BoardDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class BoardDao extends Dao {

    //글쓰기
    public boolean post(BoardDto boardDto){
        System.out.println(boardDto);
        try{
            String sql = "insert into board(btitle, bcontent, bcno, no, bfile) values(?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,boardDto.getBtitle());
            ps.setString(2,boardDto.getBcontent());
            ps.setLong(3,boardDto.getBcno());
            ps.setLong(4,boardDto.getNo());
            ps.setString(5,boardDto.getBfile());

            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    // 전체 게시물 수 반환 처리
    public int getTotalBoardSize(int bcno){
        try{
            String sql = "select count(*) as 총게시물수 from board";
            // 카테고리가 존재하면 -> 0 카테고리 없음 의미, 1 이상 카테고리의 pk 번호
            if(bcno > 0){
                sql += " where bcno = " + bcno;
            }
            System.out.println("sql = " + sql);
            //  1. 전체보기 : select count(*) as 총게시물수 from board
            //  2. 카테고리 별로 보기 : select count(*) as 총게시물수 from board where bcno = 숫자
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }


    //글 전체 호출
    public ArrayList<BoardDto> all(int startRow, int boardSize, int bcno){
        ArrayList<BoardDto> list = new ArrayList<>();
        try {
            String sql="select *from board join member on board.no = member.no";
            if(bcno > 0){
                //  일반 조건
                    //  전체보기 : 카테고리가 존재하지 않으면 생략
                    //  카테고리별 보기 : 카테고리 번호가 존재하면 where 절 추가 bcno 1 이상
                sql += " where bcno = " + bcno;
            }
                    sql += " order by board.bno desc limit ?, ?";

            System.out.println("sql = " + sql);
            //  1. 조회 2. join 테이블 3. join 조건  4. 일반(where) 조건 5. 정렬, 내림차순 6. 레코드 제한, 페이징
            ps = conn.prepareStatement(sql);
            ps.setInt(1,startRow);
            ps.setInt(2,boardSize);
            rs = ps.executeQuery();
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
    public Map<String, String> detailCall(BoardDto boardDto){
        Map<String, String> map = new HashMap<>();
        try{
            System.out.println("상세3");
            String sql = "select *from board join member on board.no = member.no join bcategory on board.bcno = bcategory.bcno where bno = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,boardDto.getBno());

            rs = ps.executeQuery();
            if(rs.next()){
                System.out.println("상세4");
                map.put("btitle",rs.getString("btitle"));
                map.put("bcname",rs.getString("bcname"));
                map.put("bno",String.valueOf(rs.getInt("bno")));
                map.put("bcontent",rs.getString("bcontent"));
                map.put("id",rs.getString("id"));
                map.put("bdate",rs.getString("bdate"));
                map.put("bview",String.valueOf(rs.getInt("bview")));
                map.put("bfile",rs.getString("bfile"));
                map.put("bcno",rs.getString("bcno"));
            }

        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println("상세5\n");
        return map;
    }

    //  글 수정
    public boolean bUpdate(Map<String, String> map){
        System.out.println("map = " + map);
        try{
            System.out.println("수정3");
            String sql = "update board set btitle = ?, bcontent = ?, bcno = ? where bno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,map.get("btitle"));
            ps.setString(2,map.get("bcontent"));
            ps.setInt(3,Integer.parseInt(map.get("bcno")));
            ps.setInt(4,Integer.parseInt(map.get("bno")));

            int count = ps.executeUpdate();
            System.out.println("count = " + count);
            System.out.println("수정4");
            if(count == 1){
                System.out.println("수정5\n");
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    //글 삭제
    public boolean bDelete(int bno){
        try{
            System.out.println("삭제3");
            String sql = "delete from board where bno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,bno);

            int count = ps.executeUpdate();
            System.out.println("삭제4");
            if(count == 1){
                System.out.println("삭제5\n");
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    //카테고리 호출
    public ArrayList<BoardDto> category(){
        ArrayList<BoardDto> list = new ArrayList<>();
        try {
            String sql = "select * from bcategory";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
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

    //  조회수 증가
    public boolean viewUpdate(int bno){
        try{
            System.out.println("조회수3");
            String sql = "update board set bview = bview + 1 where bno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,bno);

            int count = ps.executeUpdate();
            System.out.println("조회수4");
            if(count == 1){
                System.out.println("조회수5\n");
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
}