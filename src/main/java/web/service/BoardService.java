package web.service;


import jakarta.mail.Multipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import web.model.dao.BoardDao;
import web.model.dao.MemberDao;
import web.model.dto.BoardDto;
import web.model.dto.BoardPageDto;
import web.model.dto.MemberDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        if(boardDto.getUploadFile().isEmpty()){

        }else {
            String fileName = fileService.fileUpload(boardDto.getUploadFile());
            //  만약에 업로드가 실패했다면 글쓰기 실패
            if (fileName == null) {
                return false;
            }
            boardDto.setBfile(fileName);
        }
        //  boardDto 에 업로드 된 파일 명을 담기
        return boardDao.post(boardDto);
    }

    //글 전체 호출
    public BoardPageDto all(BoardPageDto pageDto) {
        System.out.println("pageDto = " + pageDto);
        if(pageDto.getPage() == 0){
            pageDto.setPage(1);
        }
        //  1. 하나의 페이지 당 표시할 게시물 수
        int boardSize = 2;      // 한 페이지 당 게시물 5개씩 출력

        //  2. 페이지 당 게시물을 출력할 시작 레코드 번호
        int startRow = (pageDto.getPage() - 1) * boardSize;

        //  3. 전체 페이지 수 구하기
            //  총 페이지 계산식 : 전체 게시물 수 / 페이지 당 게시물 수 -> 단 나머지가 존재할 경우 페이지 수 + 1
            //  ex) 총 게시물 수 : 23개, 페이지 당 5개씩 게시물 출력, 총 페이지 수 : 4페이지 + 1 -> 5페이지
            //  ex) 총 게시물 수 : 20개, 페이지 당 5개씩 게시물 출력, 총 개시물 수 : 4페이지
            //  ex) 총 게시물 수 : 15개, 페이지 당 10개씩 게시물 출력, 총 개시물 수 : 1페이지 + 1 -> 2페이지
        // 전체 게시물 수 / 페이지 당 게시물 수 했을 때 나머지가 없으면 나누기 그대로 진행, 나머지가 있다면 나누기 한 값에 + 1
        // 조건 : 카테고리별 전체 게시물 번호 세기
        int totalBoardSize = boardDao.getTotalBoardSize(pageDto.getBcno(),pageDto.getSearchKey(),pageDto.getSearchKeyword());

        int totalPage = totalBoardSize % boardSize == 0 ? totalBoardSize / boardSize : totalBoardSize / boardSize + 1;

        //  4. Dao 에 전달 -> 조건 추가(페이징 처리, 카테고리별)
        List<BoardDto> data = boardDao.all(startRow,boardSize,pageDto.getBcno(),pageDto.getSearchKey(),pageDto.getSearchKeyword());

        //  5. 페이지별 시작 번튼 번호, 끝 버튼 번호
            /*
                ex) 총 게시물 수 : 23개, 페이지 당 3개씩 게시물 출력, 7개 + 1 -> 8페이지
                                                                  몫을 활용해서 startBtn 계산
                    start 계산 식 : ((현재 페이지 -1) / 최대 버튼 수) * 최대 버튼 수 + 1
                    end 계산 식 : (start 버튼 수 + 버튼 사이즈 - 1) -> 만약 end 버튼이 마지막 페이지보다 커질 수 있다.
                    가정 : 페이지 당 최대 버튼 수를 5개로 고정            page     start       end      page - 1    /   최대 버튼 수     몫      몫 * 최대 버튼 수      + 1
                    1 페이지의 버튼 출력 : ["1"] [2] [3] [4] [5]        1         1          5          0               0 / 5       0           0                 1
                    2 페이지의 버튼 출력 : [1] ["2"] [3] [4] [5]        2         1          5          1               1 / 5       0           0                 1
                    3 페이지의 버튼 출력 : [1] [2] ["3"] [4] [5]        3         1          5          2               2 / 5       0           0                 1
                    4 페이지의 버튼 출력 : [1] [2] [3] ["4"] [5]        4         1          5          3               3 / 5       0           0                 1
                    5 페이지의 버튼 출력 : [1] [2] [3] [4] ["5"]        5         1          5          4               4 / 5       0           0                 1
                    6 페이지의 버튼 출력 : ["6"] [7] [8] [9] [10]       6         6          10         5               5 / 5       1           5                 6
                    7 페이지의 버튼 출력 : [6] ["7"] [8] [9] [10]       7         6          10         6               6 / 5       1           5                 6
                    8 페이지의 버튼 출력 : [6] [7] ["8"] [9] [10]       8         6          10         7               7 / 5       1           5                 6
                    9 페이지의 버튼 출력 : [6] [7] [8] ["9"] [10]       9         6          10         8               8 / 5       1           5                 6
                    10 페이지의 버튼 출력 : [6] [7] [8] [9] ["10"]      10        6          10         9               9 / 5       1           5                 6
                    11 페이지의 버튼 출력 : ["11"] [12] [13]            11        11         13         10              10 / 5      2           10                11
                    12 페이지의 버튼 출력 : [11] ["12"] [13]            12        11         13         11              11 / 5      2           10                11
                    13 페이지의 버튼 출력 : [11] [12] ["13"]            13        11         13         12              12 / 5      2           10                11
            * */
        int btnSize = 5 ;    //  페이지당 최대 버튼 수를 5개씩 표기한다는 가정
        int startBtn = ((pageDto.getPage() - 1) / btnSize) * btnSize + 1;       //  페이지별 시작 버튼 번호 변수
        int endBtn = startBtn + btnSize - 1;         //  페이지별 끝 버튼 번호 변수
        //  만약 끝 번호가 마지막 페이지보다 커질 수 있기 때문에 마지막 페이지로 고정
        if(endBtn >= totalPage){
            endBtn = totalPage;
        }

        return BoardPageDto.builder()
                .page(pageDto.getPage())            //  1. 현재 페이지 번호
                .totalBoardSize(totalBoardSize)     //  2. 전체 게시물 수
                .totalPage(totalPage)               //  3. 전페 페이지 수
                .data(data)                         //  4. 조회된 게시물 정보 목록 / 리스트
                .startBtn(startBtn)
                .endBtn(endBtn)
                .build();
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

    // 게시물 댓글 쓰기 처리
    public boolean bReplyWrite(Map<String, String> map){
        //  작성자(no) 는 별도의 클라이언트로 부터 입력을 받는 구조가 아니다.
            //  회원제 댓글이라는 가정 -> 로그인 정보는 로그인 객체에 저장된 상태
            //  로그인 정보를 세션 객체에 저장하는 이유 -> 그 사람이 로그인을 했는지 안 했는지 체크하기 위해서
        Object object = memberService.mLogInCheck();
        // object 타입인 이유 -> 세션 객체에는 MemberDto 타입으로 저장했는데 꺼내서 사용하기 위해서 자바 최상위 클래스인 object 타입으로 꺼내와서 형변환을 용이하게 만듬

        if(object == null){
            return false;
        }
        MemberDto loginDto = (MemberDto) object;
        int no = loginDto.getNo();

        map.put("no",String.valueOf(no));   // 작성자 id 는 int 타입이기 때문에 String.valueOf로 변환해서 넣어줌
        System.out.println("map = " + map);
        return boardDao.bReplyWrite(map);
    }

    // 게시물 댓글 출력
    public ArrayList<Map<String, String>> bReplyRead(int bno){
        return boardDao.bReplyRead(bno);
    }
}