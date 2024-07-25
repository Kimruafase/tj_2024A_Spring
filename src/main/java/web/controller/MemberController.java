package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.dto.MemberDto;
import web.service.MemberService;

import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired MemberService memberService;

    //  1. 회원가입
    @PostMapping("/signup")
    public boolean signUp(String id, String pw, String name, String email, String phone){
        MemberDto memberDto = MemberDto.builder().id(id).pw(pw).name(name).email(email).phone(phone).build();
        return memberService.signUp(memberDto);
    }

    //  2. 로그인
    @PostMapping("/login")
    public boolean logIn(String id, String pw){
        MemberDto memberDto = MemberDto.builder().id(id).pw(pw).build();
        return memberService.logIn(memberDto);
    }

    //  2-1. 로그인 체크
    @GetMapping("/login/check")
    public MemberDto mLogInCheck(){
        return memberService.mLogInCheck();
    }

    //  2-2. 로그아웃
    @GetMapping("/logout")
    public boolean mLogout(){
        return memberService.mLogOut();
    }
    //  2-3. 아이디 중복 검사
    @GetMapping("/idCheck")
    public boolean idCheck(String id){
        return memberService.idCheck(MemberDto.builder().id(id).build());
    }

    //  3. 아이디 찾기
    @PostMapping("/idSearch")
    public String idSearch(String name, String phone){
        MemberDto memberDto = MemberDto.builder().name(name).phone(phone).build();
        System.out.println(memberService.idSearch(memberDto));
        return memberService.idSearch(memberDto);
    }

    //  4. 비밀번호 찾기
    @PostMapping("/pwSearch")
    public String pwSearch(String id, String phone){
        MemberDto memberDto = MemberDto.builder().id(id).phone(phone).build();
        System.out.println(memberService.pwSearch(memberDto));
        return memberService.pwSearch(memberDto);
    }

    //  5. 내 정보 페이지
    @GetMapping("/myInfo")
    public MemberDto getMyInfo(){
        return memberService.getMyInfo();
    }

    //  6. 회원 정보 수정
    @PutMapping("/myUpdate")
    public boolean updateMyInfo(@RequestBody Map<String, String> map){      //  MAP<K,V> 로 받음
        System.out.println("map = " + map);                                 //  데이터를 잘 받았는 지 확인
        return memberService.updateMyInfo(map);
    }

    //  7. 회원 삭제
    @DeleteMapping("/myDelete")
    public boolean deleteMyInfo(String pw){
        return memberService.deleteMyInfo(pw);
    }
}
