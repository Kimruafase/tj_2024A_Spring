package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.ProductDao;
import web.model.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired private ProductDao productDao;
    @Autowired private FileService fileService;

    //  1. 제품 등록
    public boolean pRegister(ProductDto productDto){

        List<String> fileNames = new ArrayList<>();

        //  여러 개의 첨부파일 업로드하기
        //  1) 첨부파일 갯수만큼 반복문을 돌린다.
        productDto.getFiles().forEach(
                file -> {
                    //  2) 각 첨부파일 하나씩 업로드 메소드에 대입한다.
                    String fileName = fileService.fileUpload(file);
                    if(fileName != null){
                        //  3) 업로드된 파일명을 리스트에 담는다.
                        //  (업로드된 파일명을 DB에 저장하기 위해서)
                        fileNames.add(fileName);
                    } // if end
                }   // for 실행문 end
        );  // forEach end
        System.out.println(fileNames);
        productDto.setFileNames(fileNames);
        productDao.pRegister(productDto);
        return true;
    }

    //  2. 모든 제품 출력
    public List<ProductDto> productRead(){
        return productDao.productRead();
    }
}
