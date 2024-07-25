package web.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.MemberDao;
import web.model.dto.MemberDto;

import java.util.Map;

@Service
public class MemberService {

    @Autowired MemberDao memberDao;
    @Autowired HttpServletRequest request;  //  현재 요청을 보낸 클라이언트의 HTTP 요청 정보를 갖는 객체 주입

    //  1. 회원 가입
    public boolean signUp(MemberDto memberDto){
        return memberDao.signUp(memberDto);
    }

    //  2. 로그인
    public boolean logIn(MemberDto memberDto){
        int result = memberDao.logIn(memberDto);
        if(result > 0) {
            //  빌더 패턴 : 생성자가 아닌 메소드를 이용한 방식의 객체 생성
            MemberDto logInDto = MemberDto.builder().no(result).id(memberDto.getId()).build();

            //  ======== HTTP 세션 처리 =========   //
            //  1. 현재 요청을 보내온 클라이언트의 세션 객체 호출
            HttpSession httpSession = request.getSession();

            //  2. 세션 객체에 속성 추가
            httpSession.setAttribute("logInDto", logInDto);
            return true;
        }
        return false;
    }
    //  2-1. 로그인 상태 반환
    public MemberDto mLogInCheck(){
        //  1. 현재 요청을 보내온 클라이언트의 세션 객체 호출
        HttpSession session = request.getSession();

        //  2. 세션 객체 내 속성 값 호출, 타입 변환이 필수
        Object object = session.getAttribute("logInDto");

        //  3. 유효성 검사
        if(object != null){
            return (MemberDto) object;
        }
        return null;
    }

    //  2-2. 로그아웃 : 세션 초기화
    public boolean mLogOut(){
        //  현재 요청을 보내온 클라이언트의 세션 객체를 호출하고 모든 속성 값 초기화
        request.getSession().invalidate();
        return true;
    }
    //  2-3. 아이디 중복 검사
    public boolean idCheck(MemberDto memberDto){
        return memberDao.idCheck(memberDto);
    }

    //  3. 아이디 찾기
    public String idSearch(MemberDto memberDto){
        return memberDao.idSearch(memberDto);
    }

    //  4. 비밀번호 찾기
    public String pwSearch(MemberDto memberDto){
        return memberDao.pwSearch(memberDto);
    }

    //  5. 내 정보 페이지
    public MemberDto getMyInfo(){
        HttpSession session = request.getSession();
        if(session.getAttribute("logInDto") == null){   // 만약 비로그인 상태라면
            return null;
        }
        int loginNo = ( (MemberDto) session.getAttribute("logInDto")).getNo();

        return memberDao.getMyInfo(loginNo);
    }

    //  6. 회원 정보 수정
    public boolean updateMyInfo(Map<String, String> map){
        HttpSession session = request.getSession();
        if(session.getAttribute("logInDto") == null){   // 만약 비로그인 상태라면
            return false;
        }
        int loginNo = ( (MemberDto) session.getAttribute("logInDto")).getNo();  // 로그인한 회원 번호를 int 타입으로 저장
        return memberDao.updateMyInfo(map,loginNo);
    }

    //  7. 회원 삭제
    public boolean deleteMyInfo(String pw){
        HttpSession session = request.getSession();
        if(session.getAttribute("logInDto") == null){   // 만약 비로그인 상태라면
            return false;
        }
        int loginNo = ( (MemberDto) session.getAttribute("logInDto")).getNo();
        return memberDao.deleteMyInfo(pw,loginNo);
    }
}
