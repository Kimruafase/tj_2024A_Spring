package web.service;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.UUID;

@Service
public class FileService {

    //  첨부파일을 저장(복사 / 이동)할 경로 만들기
    String uploadPath = "C:\\Users\\tj-bu-703-020\\Desktop\\tj_2024A_Spring\\src\\main\\resources\\static\\upload\\";


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

    @Autowired private HttpServletRequest request;      // HTTP 요청 객체, HTTP 로 요청 들어온 정보와 기능이 포함된 객체
    @Autowired private HttpServletResponse response;    // HTTP 응답 객체, HTTP 로 응답할 때 정보와 기능이 포함된 객체


    //  2. 파일 다운로드
    public void fileDownLoad(String filename){
        try {
            //   1) 다운로드할 경로 설정 uploadPath
            //  업로드된 경로와 다운로드할 파일명 조합
            String downLoadPath = uploadPath + filename;

            //  File 클래스는 file 과 관련된 다양한 메소드를 제공
                //  exists() : 해당 경로에 파일이 존재하면 true 없으면 false 반환
                //  length() : 해당 경로에 파일이 존재하면 파일의 용량을 byte 의 개수로 반환(파일 용량 찾기)
            File file = new File(downLoadPath);

            if(!file.exists()){     // 만약 파일이 존재하지 않으면 메소드를 빠져나감
                return;
            }

            //  2) 해당 다운로드할 파일을 서버(JAVA)로 byte 를 읽기
            //  Stream 이란 ? 자바 외부와 통신 시 byte 가 다니는 통로
            //  InputStream : 읽어들이는 통로
            //  OutputStream : 내보내는 통로
            //  Buffer : Stream 내 통로에서 이동하는 동안 일시적으로 데이터를 보관하는 메모리(Stream 에서도 사용)
                //  2-1) 파일 입력 Stream 객체 생성 new BufferedInputStream(입력할 대산의 stream 객체)
            BufferedInputStream fin = new BufferedInputStream(new FileInputStream(downLoadPath));

                //  2-2) 파일의 용량만큼 배열 선언
                    //  파일의 용량 크기
            long fileSize = file.length();
                    //  파일의 용량 크기만큼의 배열 선언
            byte[] bytes = new byte[(int) fileSize];
            fin.read(bytes);     // 경로에 해당하는 파일을 byte 로 가져오기

            System.out.println(Arrays.toString(bytes));
            fin.close();
            // ============================================== 읽어온 byte 배열을 HTTP byte 형식으로 응답하기 //

            //  3) HTTP Stream 으로 응답하기
                //  3-1) 출력 Stream 객체 생성, new BufferedOutputStream(출력할 대상의 stream 객체)
                    //  response.getOutputStream) : HTTP 응답 할 때 byte 형식의 stream 객체 사용
            BufferedOutputStream fout = new BufferedOutputStream(response.getOutputStream());

            //  HTTP 응답의 헤더 속성 추가 .setHeader(key, value)
                //  Content-Disposition : 브라우저가 제공하는 다운로드 형식
                //  attachment;filename : "다운로드에 표시될 파일명"
                    //  URLEncoder.encode() : URL 경로상의 한글을 인코딩
                    //  filename.split("_")[1] : "_" 기준으로 분해해서 UUID 를 제외한 실제 파일명만 다운로드
            response.setHeader("Content-Disposition","attachment;filename=abc" + URLEncoder.encode(filename.split("_")[1],"utf-8"));
                //  3-2) byte 배열 내보내기 / 출력 / 쓰기
            fout.write(bytes);
            fout.close();
        }catch (Exception e) {
            System.out.println(e);
        }
    }

}
