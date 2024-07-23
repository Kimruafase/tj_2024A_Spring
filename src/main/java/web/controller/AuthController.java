package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.service.AuthService;

@RestController             //  @Controller + @ResponseBody
@RequestMapping("/auth")    //  메소드에게 공통 매핑 주입
public class AuthController {


    @Autowired AuthService authService;

    //  1. 인증 번호 요청 구현
    @GetMapping("/code")
    public boolean authCodeGet(String email){
        return authService.authCodeGet(email);
    }


    //  2. 인증 번호 인증 구현
    @PostMapping("/check")
    public boolean authCodeResult(String authCodeInput){
        return authService.authCodeResult(authCodeInput);
    }


}
