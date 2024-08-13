package web.model.dao;


import org.springframework.stereotype.Component;
import web.model.dto.ProductDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDao extends Dao{

    public boolean pRegister(ProductDto productDto){

        System.out.println("productDto = " + productDto);

        //  각 테이블에 따른 DTO 정보를 각각 Insert 한다

        try{
            //  제품 등록
            String sql = "insert into producttable(pname, pinformation, pprice) values(?, ?, ?)";

            // JDBC 에서 insert 한 레코드의 자동번호(auto_increment)가 부여된 pk 번호를 반환 받는 방법
                //  1. conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS), GENERATED : 생성됨
                //  2. ResultSet pkRs = ps.getGeneratedKeys();
                //  3. pkRs.next();
                //  4. int pk = pkRs.getInt(1);
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1,productDto.getPname());
            ps.setString(2, productDto.getPinformation());
            ps.setInt(3,productDto.getPprice());
            int count = ps.executeUpdate();
            if (count == 1) {   // 등록된 레코드가 1개이면 제품 등록 성공
                //  제품 이미지 등록
                ResultSet pkRs = ps.getGeneratedKeys();         // 생성된 pk번호들을 ResultSet 으로 반환 받음
                if(pkRs.next()){    //  ResultSet.next(); -> 다음 레코드 -> pk가 1개 존재하면
                    int pno = pkRs.getInt(1);
                    System.out.println("pno = " + pno);
                    for (int i = 0; i < productDto.getFileNames().size(); i++) {    // productDto 의 List<String> fileNames 필드의 길이만큼 반복문 실행
                        try{
                            String sql2 = "insert into productImageTable(pimg, pno) values(?, ?)";
                            String files = productDto.getFileNames().get(i);        // productDto 의 List<String> fileNames 필드의 i번째 값을 string 타입의 files 지역변수에 저장
                            PreparedStatement ps2 = conn.prepareStatement(sql2);
                            ps2.setString(1,files);
                            ps2.setInt(2,pno);
                            ps2.executeUpdate();
                        } catch (Exception e){
                            System.out.println("에러 정보는 " + e);
                        }
                    }

                }
            }

        }catch (Exception e){
            System.out.println("에러 정보는 " + e );
        }
        return true;
    }

    public List<ProductDto> productRead(){
        List<ProductDto> list = new ArrayList<>();
        try{
            //  1. 제품 조회
            String sql = "select *from productTable";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){

                //  2. 제품 이미지 조회
                List<String> fileNames = new ArrayList<>();
                    // 해당 제품의 모든 이미지 조회
                String sql2 = "select *from productImageTable where pno = ?";
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ps2.setInt(1,rs.getInt("pno"));
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()){
                    fileNames.add(rs2.getString("pimg"));
                }

                //  빌더를 통해서 ProductDto 를 생성하고 바로 list 에 add 함
                list.add(ProductDto.builder()
                        .pname(rs.getString("pname"))
                        .pinformation(rs.getString("pinformation"))
                        .pprice(rs.getInt("pprice"))
                        .pdate(rs.getString("pdate"))
                        .pno(rs.getInt("pno"))
                        .pview(rs.getInt("pview"))
                        .fileNames(fileNames)
                        .build());
            }
            System.out.println(list);
        }catch (Exception e){
            System.out.println("에러 정보는 " + e);
        }
        return list;
    }
}
