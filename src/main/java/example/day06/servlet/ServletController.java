package example.day06.servlet;
/*

    서블릿 : JAVA 를 사용해서 웹 페이지를 동적으로 생성하는 서버 측의 프로그램
        > 웹 서버의 성능을 향상시키기 위해 사용되는 일종의 클래스
        > 즉 JAVA 회사에서 웹 개발을 하기 위한 웹 관련 클래스를 제공한다.
        > 서블릿 사용 방법
            > 1. 해당 클래스에 extends HttpServlet -> String 클래스와 동일
            > 2. 외부로부터 해당 클래스를 연결 / 매핑할 수 있도록 HTTP URL(온라인, 인터넷 웹 주소) 정의
                > 해당 클래스에 @(어노테이션)WebServlet("/URL") 이용
            > 3. HttpServlet 의 다양한 메소드 제공
                > doGet() : HTTP 를 이용한 서버로 부터 요청이 들어올 때 HTTP 의 메소드가 GET 이면 호출되는 함수
* */


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//  http://서버 ip:서버 port/day06/servlet
//  http://localhost:8080/day06/servlet
//  http://192.168.30.252/day06/servlet
@WebServlet("/day06/servlet")
public class ServletController extends HttpServlet {
    //  extends : 상속(해당 클래스에 특정 클래스로부터 상속받으면 해당 클래스는 특정 클래스로부터 모든 필드 / 메소드를 사용할 수 있다.)
        //  @Override : 오버라이딩 -> 상속 받은 클래스의 메소드를 재정의

    //  0. init()
    @Override
    public void init() throws ServletException {
        System.out.println("ServletController.init");
        System.out.println("해당 클래스의 서블릿 객체가 생성되었다.");
        super.init();
    }

    //  0. service()
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.service");
        System.out.println("해당 클래스의 서블릿 객체 서비스가 실행되었다.");
        super.service(req, resp);
    }

    //  0. destroy()
    @Override
    public void destroy() {
        System.out.println("ServletController.destroy");
        System.out.println("해당 클래스의 서블릿 객체가 초기화 되었다.");
        super.destroy();
    }

    //  1. doGet()
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doGet");
        System.out.println("HTTP의 get 메소드 방식으로 요청이 들어왔습니다. ");
        //  > 요청 데이터 : 매개변수처럼 HTTP 요청 시 들어오는 데이터, HTTP 요청 정보 관련 객체 : HttpServletRequest
        System.out.println("HTTP get Method Request Data : " + req.getParameter("data"));
        //  > 응답 데이터 : 리턴값처럼 HTTP 응답 시 반환하는 데이터, HTTP 응답 정보 관련 객체 : HttpServletResponse
            // http://localhost:8080/day06/servlet?data=serverHi
        resp.getWriter().print("HTTP get Method Response Data : [get] clientHi");
        //  super.doGet(req, resp);     // super.메소드 : 부모(상위) 클래스의 메소드를 호출
    }

    //  2. doPost()
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doPost");
        System.out.println("HTTP의 post 메소드 방식으로 요청이 들어왔습니다.");
        //  1. 요청 데이터
        System.out.println("request Data : " + req.getParameter("data"));
        //  2. 등답 데이터
        resp.getWriter().print("response Data : [post] clientHi");
        //  super.doPost(req, resp);
    }

    //  3. doPut()
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doPut");
        System.out.println("HTTP의 put 메소드 방식으로 요청이 들어왔습니다.");
        //  1. 요청 데이터
        System.out.println("request Data : " + req.getParameter("data"));
        //  2. 등답 데이터
        resp.getWriter().print("response Data : [put] clientHi");
        //  super.doPut(req, resp);
    }

    //  4. doDelete()
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ServletController.doDelete");
        System.out.println("HTTP의 delete 메소드 방식으로 요청이 들어왔습니다.");
        //  1. 요청 데이터
        System.out.println("request Data : " + req.getParameter("data"));
        //  2. 등답 데이터
        resp.getWriter().print("response Data : [delete] clientHi");
        //  super.doDelete(req, resp);
    }
}
