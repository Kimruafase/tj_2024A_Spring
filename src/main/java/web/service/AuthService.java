package web.service;


import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired HttpServletRequest request;

    //  1. 인증 번호 요청 구현
    public boolean authCodeGet(String email){
        try {
            //  인증 번호가 문자인 이유 : 앞자리에 0이 들어가야해서 + 문자열을 써야 이어붙이기 가능
            int randomCode = (int) (Math.random() * 10);
            String authCode = String.valueOf(randomCode);
            for (int i = 0; i < 5; i++) {
                int randomCode1 = (int) (Math.random() * 10);
                authCode += randomCode1;
            }
            System.out.println("authCode = " + authCode);
            // (선택) DB : 영구적인 데이터 vs JVM (스택, 힙, 메소드) vs 세선(웹 서버 저장소 - 요청하는 클라이언트의 브라우저마다 있다)
            //  1. 서버 세션의 인증코드 저장
            request.getSession().setAttribute("authCode", authCode);

            //  2. 서버 세션의 생명주기 설정(세션 유지 시간)
            request.getSession().setMaxInactiveInterval(10);

            //  3. 이메일 전송
//            emailSend(email,"000 홈페이지의 회원가입 인증 코드","인증코드 : " + authCode);

            return true;
        } catch (Exception e){
            System.out.println(e);
        }
        return false;
    }


    //  2. 인증 번호 인증 구현
    public boolean authCodeResult(String authCodeInput){
        System.out.println("authCodeInput = " + authCodeInput);
        //  1. 인증 번호 호출
        Object object = request.getSession().getAttribute("authCode");
        System.out.println("object = " + object);
        if(object != null){
            System.out.println("object = " + object);
            String authCode = (String) object;
            //  2. 입력 받은 인증번호와 비교
            if(authCode.equals(authCodeInput)){
                System.out.println("authCode = " + authCode);
                System.out.println(authCode.equals(authCodeInput));
                return true;    // 동일하면 true
            }
        }
        return false;
    }

    @Autowired JavaMailSender javaMailSender;

    //  3. 이메일 전송 함수, 매개변수 : 받는 사람의 이메일, 메일 제목, 메일 내용
    public void emailSend(String toEmail, String subject, String content){
        try {
            //  1. 메일 내용들을 포멧 / 형식 맞추기 위해 MIME 프로토콜
            //  MIME 객체 생성
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            //  2. 메일 내용 구성
            //  new MimeMessageHelper(mime 객체, 첨부파일 여부, 인코딩 타입)
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");

            //  3. 메일 보내는 사람
            mimeMessageHelper.setFrom("ppooppplus@naver.com");

            //  4. 메일 받는 사람
            mimeMessageHelper.setTo(toEmail);

            //  5. 메일 제목
            mimeMessageHelper.setSubject(subject);

            //  6. 메일 내용
            mimeMessageHelper.setText(content);

            //  7. *** 메일 전송
            javaMailSender.send(mimeMessage);   // mime 객체를 보내기
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
