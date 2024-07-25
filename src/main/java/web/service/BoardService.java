package web.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.BoardDao;
import web.model.dao.MemberDao;
import web.model.dto.BoardDto;
import web.model.dto.MemberDto;

import java.util.ArrayList;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    BoardDao boardDao;
    @Autowired
    MemberService memberService;

    //글쓰기
    public boolean post(Map<String , String> map){

        MemberDto loginDto = memberService.mLogInCheck();
        if( loginDto == null ) return false;
        int loginNo = loginDto.getNo();
        // 2. 로그인된 회원번호를 map 엔트리 추가
        map.put( "no" , String.valueOf(loginNo) );

        return boardDao.post(map);
    }

    //글 전체 호출
    public ArrayList<BoardDto> all() {
        return boardDao.all();
    }

    //글 상세 호출

    //글 수정

    //글 삭제

    //카테고리 호출
    public ArrayList<BoardDto> category(){

        return boardDao.category();
    }
}