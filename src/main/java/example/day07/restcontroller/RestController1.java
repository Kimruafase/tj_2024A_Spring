package example.day07.restcontroller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

//  including RESTful : HTTP 기반의 자원을 제공하는 인터페이스 구축( CRUD )
    //  SPRING WEB 이 아닌 환경 : servlet 클래스 직접 구현
    //  SPRING WEB 환경 : MVC2 3티어 제공하여 controller 가 자동으로 서블릿 등록

// 해당 클래스가 SPRING MVC 에서 controller 클래스임을 등록, 스프링 컨테이너(전역 저장소) 빈(객체) 등록
    //  제어 역전 (IOC) : 객체 관리를 개발자가 아닌 스프링이 해준다.
    //  why? 여러 개발자가 공통으로 사용할 객체는 1명이 관리하면 좋은데 그것을 spring 이 대신 해준다.
@Controller
public class RestController1 {
    //int data = 10;
//    @RequestMapping(value = "해당 메소드의 mapping 할 HTTP 주소")
    //  value : "(http://localhost:8080)ip 와 주소 생략 /example/test1"
        // 서버 내 동일한 URL 을 정의할 수 없지만 HTTP 메소드가 다르기 때문에 동일한 주소 정의가 가능하다.
    //  method : RequestMethod.HTTP 메소드명 : GET, POST, PUT, DELETE

    //  1. HTTP GET         http://localhost:8080/example/rest1
    @RequestMapping(value = "/example/rest1",method = RequestMethod.GET)
    public void getRest1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("RestController1.getRest1");

        //  1. 요청
        String key = request.getParameter("key");
        System.out.println("key = " + key);

        //  2. 응답
        response.getWriter().println("[GET] Client HI");

    }

    //  2. HTTP POST        http://localhost:8080/example/rest1
    @RequestMapping(value = "/example/rest1",method = RequestMethod.POST)
    public void postRest1(HttpServletRequest request, HttpServletResponse response)throws IOException {
        System.out.println("RestController1.postRest1");

        //  1. 요청
        String key = request.getParameter("key");
        System.out.println("key = " + key);

        //  2. 응답
        response.getWriter().println("[POST] Client HI");
    }

    //  3. HTTP PUT         http://localhost:8080/example/rest1
    @RequestMapping(value = "/example/rest1",method = RequestMethod.PUT)
    public void putRest1(HttpServletRequest request, HttpServletResponse response)throws IOException {
        System.out.println("RestController1.putRest1");

        //  1. 요청
        String key = request.getParameter("key");
        System.out.println("key = " + key);

        //  2. 응답
        response.getWriter().println("[PUT] Client HI");
    }

    //  3. HTTP DELETE      http://localhost:8080/example/rest1
    @RequestMapping(value = "/example/rest1",method = RequestMethod.DELETE)
    public void deleteRest1(HttpServletRequest request, HttpServletResponse response)throws IOException {
        System.out.println("RestController1.deleteRest1");

        //  1. 요청
        String key = request.getParameter("key");
        System.out.println("key = " + key);

        //  2. 응답
        response.getWriter().println("[DELETE] Client HI");
    }

}
