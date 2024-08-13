package web.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    //  제품 정보
    private int pno;
    private String pdate;
    private int pview;
    private String pname;
    private String pinformation;
    private int pprice;

    //  여러 개의 첨부파일 필드
    List<MultipartFile> files;

    //  여러 개의 파일명 필드
    List<String> fileNames;
}
