package web.model.dao;

import org.springframework.stereotype.Component;

@Component
public class MemberDao extends Dao{

    public boolean signUp(String id, String pw, String name, String email, String phone){
        try{
            String sql = "insert member(id,pw,name,email,phone) values(?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,id);
            ps.setString(2,pw);
            ps.setString(3,name);
            ps.setString(4,email);
            ps.setString(5,phone);

            int count = ps.executeUpdate();
            if(count == 1) {
                return true;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
}
