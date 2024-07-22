package web.model.dao;

import org.springframework.stereotype.Component;
import web.model.dto.MemberDto;

@Component
public class MemberDao extends Dao{

    //  1. 회원가입
    public boolean signUp(MemberDto memberDto){
        try{
            String sql = "insert member(id,pw,name,email,phone) values(?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, memberDto.getId());
            ps.setString(2, memberDto.getPw());
            ps.setString(3, memberDto.getName());
            ps.setString(4, memberDto.getEmail());
            ps.setString(5, memberDto.getPhone());

            int count = ps.executeUpdate();
            if(count == 1) {
                return true;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    //  2. 로그인
    public int logIn(MemberDto memberDto){
        try{
            String sql = "select *from member where id = ? and pw = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, memberDto.getId());
            ps.setString(2, memberDto.getPw());

            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("no");
            }
        }catch (Exception e){
            System.out.println();
        }
        return 0;   //  회원 번호를 못 찾아서 0 반환
    }

    //  2-3. 아이디 중복 검사
    public boolean idCheck(MemberDto memberDto){
        try{
            String sql = "select *from member where binary(id) = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,memberDto.getId());

            rs = ps.executeQuery();
            if(rs.next()){
                return false;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return true;
    }

    //  3. 아이디 찾기
    public String idSearch(MemberDto memberDto){
        try {
            String sql = "select *from member where name = ? and phone = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,memberDto.getName());
            ps.setString(2,memberDto.getPhone());

            rs = ps.executeQuery();
            if (rs.next()){
                return rs.getString("id");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    //  4. 비밀번호 찾기
    public String pwSearch(MemberDto memberDto){
        try {
            String sql = "select *from member where id= ? and phone = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,memberDto.getId());
            ps.setString(2,memberDto.getPhone());

            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString("pw");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public MemberDto getMyInfo(int no){
        try{
            String sql = "select *from member where no = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,no);

            rs = ps.executeQuery();
            if(rs.next()){
                return MemberDto.builder()
                        .id(rs.getString("id"))
                        .no(rs.getInt("no"))
                        .name(rs.getString("name"))
                        .phone(rs.getString("phone"))
                        .email(rs.getString("email"))
                        .build();
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }


}
