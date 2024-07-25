package web.model.dto;


import jakarta.mail.Multipart;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder @Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    private long bno ;          // 번호
    private String btitle;      // 제목
    private String bcontent;        // 내용
    private MultipartFile uploadFile;       // 첨부파일
        //  HTML 의 Input type 이 file 일 때 byte 로 매핑할 때 사용되는 인터페이스
        //  업로드 시 byte를 저장하고 있는 필드
    private String bfile;
        //  DB 에 저장 / 출력할 업로드된 파일명 필드
    private long bview ;        // 조회수
    private String bdate;       // 작성일
    // 카테고리
    private long bcno ;         // 카테고리 번호
    private String bcname ;         // 카테고리 이름
    // 회원
    private long no ;          // 작성자 번호
    private String id;          // 작성자 아이디
}