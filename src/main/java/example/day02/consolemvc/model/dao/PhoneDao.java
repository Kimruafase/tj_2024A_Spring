package example.day02.consolemvc.model.dao;

import example.day02.consolemvc.model.dto.PhoneDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PhoneDao {
    private static PhoneDao phoneDao = new PhoneDao();
    private PhoneDao(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springExample","root","1234");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static PhoneDao getInstance(){
        return phoneDao;
    }
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    //  1.
    public boolean postPhone(PhoneDto phoneDto){
        try{
            String sql = "insert into phoneBook(name, phone) values(?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,phoneDto.getName());
            ps.setString(2,phoneDto.getPhone());
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    //  2.
    public ArrayList<PhoneDto> getPhone(){
        ArrayList<PhoneDto> list = new ArrayList<>();
        try{
            String sql = "select *from phoneBook";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String phone = rs.getString(3);
                PhoneDto phoneDto = new PhoneDto(id,name,phone);
                list.add(phoneDto);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }
}
