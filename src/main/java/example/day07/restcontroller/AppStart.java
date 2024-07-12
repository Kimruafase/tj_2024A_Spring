package example.day07.restcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//  1. 내장 톰캣(웹 서버) 실행,
//  2. 동일 패키지 또는 하위 패키지들의 MVC 어노테이션(@Controller)들을 사용하는 클래스들을 스캔해서 빈 등록 (상위 패키지는 불가능)
@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    }

}
