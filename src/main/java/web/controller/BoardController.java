package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.dto.BoardDto;
import web.model.dto.BoardPageDto;
import web.service.BoardService;
import web.service.MemberService;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    //글쓰기
    @PostMapping("/post")
    public boolean post( BoardDto boardDto ){
        System.out.println(boardDto);
        return boardService.post(boardDto);
    }

    //글 전체 호출
    @GetMapping("/call")
    public BoardPageDto all(BoardPageDto pageDto) {
        System.out.println("pageDto = " + pageDto); // 1. page          : 현재 페이징 처리에서 사용할 페이지 번호
                                                    // 2. bcno          : 현재 선택된 카테고리 번호
                                                    // 3. searchKey     : 검색 조회 시 사용되는 필드명
                                                    // 4. searchKeyword : 검색 조회 시 사용되는 필드의 값
        return boardService.all(pageDto);
    }

    //글 상세 호출
    @GetMapping("/detailcall")
    public Map<String, String> detailCall(int bno){
        System.out.println("상세1");
        return boardService.detailCall(bno);
    }

    //글 수정
    @PutMapping("/update")
    public boolean bUpdate(@RequestBody Map<String, String> map){
        System.out.println("수정1");
        return boardService.bUpdate(map);
    }

    //글 삭제
    @DeleteMapping("/delete")
    public boolean bDelete(int bno){
        System.out.println("삭제1");
        return boardService.bDelete(bno);
    }

    //카테고리 호출
    @GetMapping("/category")
    public ArrayList<BoardDto> bCategory(){
        return boardService.category();
    }

    // 조회수 증가
    @GetMapping("/viewupdate")
    public boolean viewUpdate(int bno){
        System.out.println("조회수1");
        return boardService.viewUpdate(bno);
    }

    // 게시물의 댓글 쓰기 처리
    @PostMapping("/reply/write")    // 쓰기는 post mapping 을 주로 사용한다. (CRUD 중에서)
    public boolean bReplyWrite(@RequestBody Map<String, String> map){
        // RequestBody 를 사용하는 이유 JS 에서 JSON 으로 보내는데 JAVA 에서 사용하기 위해서 객체(DTO 나 컬렉션 프레임 워크 등)로 변환
        // 왜 map 을 쓰는가 -> dto 를 사용하지 않고 map 의 저장기능 사용
        System.out.println("BoardController.bReplyWrite");
        System.out.println("map = " + map);
        return boardService.bReplyWrite(map);
    }

    // 게시물의 댓글 출력
    @GetMapping("/reply/read")
    public ArrayList<Map<String, String>> bReplyRead(int bno){
        return boardService.bReplyRead(bno);
    }
}