package web.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileService {


    //  1. 파일 업로드
    public String fileUpload(MultipartFile multipartFile){
        try {
        //  파일처리

        System.out.println("multipartFile = " + multipartFile);
//        byte[] bytes = multipartFile.getBytes();
        System.out.println(multipartFile.getContentType());     //  파일 확장자
        System.out.println(multipartFile.getName());            //  속성명
        System.out.println(multipartFile.getSize());            //  용량(byte 사이즈)
        System.out.println(multipartFile.isEmpty());            //  파일이 없으면 true, 있으면 false


        //  1) 파일 실제 이름 추출
            //  + 클라이언트(유저)들이 서로 다른 파일 내용의 같은 파일명으로 업로드 했을 때 식별이 불가능
            //  해결 방안 [1] UUID(고유성을 보장하는 ID 규약) 2. 조합식 별 설계(주로 업로드 날짜 / 시간 과 파일명 조합)
        String uuid = UUID.randomUUID().toString();     //  난수의 UUID 생성, 임의의 UUID 규약에 따른 문자열 생성
        System.out.println("uuid = " + uuid);
        String fileName = multipartFile.getOriginalFilename();
        System.out.println("fileName = " + fileName);

        //  UUID 와 파일명 합치기, uuid 와 파일명을 구분하는 문자 조합
            //  "_" 기준으로 분리하면 [0] uuid, [1] 순수 파일명으로 분리 가능
            //  "문자열".replaceAll("기존문자" , "새로운 문자") : 만약에 문자열 내 기존 문자가 존재하면 새로운 문자로 치환해서 반환
        fileName = uuid + "_" + fileName.replaceAll("_","-");   // 파일명 내에 "_"가 존재하면 "-"로 변경

        //  2) 첨부파일을 저장(복사 / 이동)할 경로 만들기
        String uploadPath = "C:\\Users\\tj-bu-703-020\\Desktop\\tj_2024A_Spring\\src\\main\\resources\\static\\upload\\";

        //  3) 저장할 경로와 파일명 합치기
        String filePath = uploadPath + fileName;

        //  4) 해당 결로로 설정한 file 객체, transferTo (file 객체)
        File file = new File(filePath);

            //  5) transferTo (file 객체) : file 객체 내 설정한 해당 경로로 파일 복사 / 저장 / 이동
            multipartFile.transferTo(file);
            return fileName;
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }




    //  2. 파일 다운로드


}
