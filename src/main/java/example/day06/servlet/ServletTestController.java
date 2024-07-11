package example.day06.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
/*
    day06 폴더에서 서블릿 클래스 만들기
    해당 서블릿 클래스의 주소는 /day06/test
    해당 서블릿 주소 뒤에 value 값 10 보내기
    쿼리 스트링 방식으로 데이터와 함께 주소 요청
    get 메소드는 +2 응답 = 12
    post 메소드는 *2 응답 = 20
    put 메소드는 / 2 응답 = 5
    delete 메소드는 % 2 응답 = 0
* */
@WebServlet("/day06/test")
public class ServletTestController extends HttpServlet {
    int data = 10;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  요청 데이터
        System.out.println("request data : " + req.getParameter("value"));
        //  응답 데이터
        resp.getWriter().println(data + 2);
       // super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  요청 데이터
        System.out.println("request data : " + req.getParameter("value"));
        //  응답 데이터
        resp.getWriter().println(data * 2);

        //  super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  요청 데이터
        System.out.println("request data : " + req.getParameter("value"));
        //  응답 데이터
        resp.getWriter().println(data / 2);
        //  super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  요청 데이터
        System.out.println("request data : " + req.getParameter("value"));
        //  응답 데이터
        resp.getWriter().println(data % 2);
        // super.doDelete(req, resp);
    }
}
