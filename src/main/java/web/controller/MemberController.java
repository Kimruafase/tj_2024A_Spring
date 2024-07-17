package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import web.model.dto.MemberDto;
import web.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired MemberService memberService;

    @PostMapping("/signup")
    public boolean signUp(String id, String pw, String name, String email, String phone){
        MemberDto memberDto = MemberDto.builder().id(id).pw(pw).name(name).email(email).phone(phone).build();
        return memberService.signUp(memberDto);
    }

    @PostMapping("/login")
    public boolean logIn(String id, String pw){
        MemberDto memberDto = MemberDto.builder().id(id).pw(pw).build();
        return memberService.logIn(memberDto);
    }

}
