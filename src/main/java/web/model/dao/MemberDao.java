package web.model.dao;

import org.springframework.stereotype.Component;
import web.model.dto.MemberDto;

@Component
public class MemberDao extends Dao{

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

    public boolean logIn(MemberDto memberDto){
        try{
            String sql = "select *from member where id = ? and pw = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, memberDto.getId());
            ps.setString(2, memberDto.getPw());

            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (Exception e){
            System.out.println();
        }
        return false;
    }
}
