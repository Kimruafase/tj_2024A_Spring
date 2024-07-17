package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.MemberDao;
import web.model.dto.MemberDto;

@Service
public class MemberService {

    @Autowired MemberDao memberDao;

    public boolean signUp(MemberDto memberDto){
        return memberDao.signUp(memberDto);
    }

    public boolean logIn(MemberDto memberDto){
        return memberDao.logIn(memberDto);
    }
}
