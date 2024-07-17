package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.MemberDao;

@Service
public class MemberService {

    @Autowired MemberDao memberDao;

    public boolean signUp(String id, String pw, String name, String email, String phone){
        return memberDao.signUp(id,pw,name,email,phone);
    }
}
