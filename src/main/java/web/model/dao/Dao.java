package web.model.dao;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class Dao {

    //  DB 연동하는 부모 클래스 로 사용 //

    public Connection conn;
    public PreparedStatement ps;
    public ResultSet rs;
    public Dao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springweb", "root", "1234");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
