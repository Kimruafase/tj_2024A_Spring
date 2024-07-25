package web.controller;

//  AJAX 통신용이 아닌 템플릿 반환하는 컨트롤러 //


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//  @RestController             //  @Controller + @ResponseBody(응답 JSON 객체)
@Controller                     //  JSON 객체가 아닌 템플릿 파일 반한하므로 @ResponseBody 없이 사용 가능
public class ViewController {
    //  1. 레이아웃
    @GetMapping("/")    //  http://localhost:8080                //  페이지 요청은 HTTP 의 get 방식을 주로 사양한다.
    public String index(){
        return "index.html";          // templates 폴더 내 반환할 경로의 파일명
    }
    //  2. 회원 관련 경로 설정
    @GetMapping("/member/signup")
    public String mSignUp(){
        return "/member/signUp.html";
    }
    @GetMapping("/member/login")
    public String mLogIn(){
        return "/member/logIn.html";
    }
    @GetMapping("/member/search")
    public String mSearch(){
        return "/member/search.html";
    }
    @GetMapping("/member/mypage")
    public String mMypage(){
        return "/member/myinfo.html";
    }
    @GetMapping("/member/update")
    public String mUpdate(){
        return "/member/update.html";
    }
    @GetMapping("/member/leave")
    public String mLeave(){
        return "/member/leave.html";
    }

    // 게시물 전체 페이지 출력
    @GetMapping("/board/all")
    public String boardAllPrint(){
        return "/board/all.html";
    }

    // 게시물 쓰기 페이지 출력
    @GetMapping("/board/write")
    public String boardWrite(){
        return "/board/write.html";
    }

    // 게시물 개별 페이지 출력
    @GetMapping("/board/detail")
    public String boardDetail(){
        return "/board/detail.html";
    }

    // 게시물 수정 페이지 출력
    @GetMapping("/board/update")
    public String boardUpdate(){
        return "/board/update.html";
    }

}
