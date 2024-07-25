package web.service;


import jakarta.mail.Multipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import web.model.dao.BoardDao;
import web.model.dao.MemberDao;
import web.model.dto.BoardDto;
import web.model.dto.MemberDto;

import java.util.ArrayList;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    BoardDao boardDao;
    @Autowired
    MemberService memberService;
    @Autowired FileService fileService;

    //글쓰기
    public boolean post(BoardDto boardDto){


//        MultipartFile multipartFile = boardDto.getBfile();
//        System.out.println("multipartFile = " + multipartFile);
////        byte[] bytes = multipartFile.getBytes();
//        System.out.println(multipartFile.getContentType());     //  파일 확장자
//        System.out.println(multipartFile.getName());            //  속성명
//        System.out.println(multipartFile.getSize());            //  용량(byte 사이즈)
//        System.out.println(multipartFile.isEmpty());            //  파일이 없으면 true, 있으면 false


        System.out.println(boardDto);
        MemberDto loginDto = memberService.mLogInCheck();
        if( loginDto == null ) {
            return false;
        }
        int loginNo = loginDto.getNo();
        // 2. 로그인된 회원번호를 map 엔트리 추가
        boardDto.setNo(loginNo);

        //  파일처리
        String fileName = fileService.fileUpload(boardDto.getUploadFile());
        //  만약에 업로드가 실패했다면 글쓰기 실패
        if(fileName == null){
            return false;
        }
        //  boardDto 에 업로드 된 파일 명을 담기
        boardDto.setBfile(fileName);
        return boardDao.post(boardDto);
    }

    //글 전체 호출
    public ArrayList<BoardDto> all() {
        return boardDao.all();
    }

    //글 상세 호출
    public Map<String, String> detailCall(int bno){
        System.out.println("상세2");
        return boardDao.detailCall(BoardDto.builder().bno(bno).build());
    }

    //글 수정
    public boolean bUpdate(Map<String, String> map){
        System.out.println("수정2");
        return boardDao.bUpdate(map);
    }

    //글 삭제
    public boolean bDelete(int bno){
        System.out.println("삭제2");
        return boardDao.bDelete(bno);
    }

    //카테고리 호출
    public ArrayList<BoardDto> category(){

        return boardDao.category();
    }

    // 조회수 증가
    public boolean viewUpdate(int bno){
        System.out.println("조회수2");
        return boardDao.viewUpdate(bno);
    }
}