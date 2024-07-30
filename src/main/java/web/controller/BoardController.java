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
        System.out.println("pageDto = " + pageDto); // 1. 현재 페이징 처리에서 사용할 페이지 번호
                                                    // 2. 현재 선택된 카테고리 번호
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

    @GetMapping("/viewupdate")
    public boolean viewUpdate(int bno){
        System.out.println("조회수1");
        return boardService.viewUpdate(bno);
    }


}