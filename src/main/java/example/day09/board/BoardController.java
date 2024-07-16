package example.day09.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired BoardService boardService;

    //  1. get
    @GetMapping("/get1")
    public ArrayList<BoardDto> boardGet1(){
        ArrayList<BoardDto> list = boardService.boardGet1();
        return list;
    }
    @GetMapping("/get2")
    public BoardDto boardGet2(int pno){
        BoardDto boardDto = boardService.boardGet2(pno);
        return boardDto;
    }
    //  2. post
    @PostMapping("/post")
    public boolean boardPost(String title, String detail, String pw){
        return boardService.boardPost(title,detail,pw);
    }
    //  3. put
    @PutMapping("/put")
    public boolean boardPut(String title, String detail, String pw, int pno){
        return boardService.boardPut(title, detail, pw, pno);
    }

    //  4. delete
    @DeleteMapping("/delete")
    public boolean boardDelete(String pw, int pno){
        return boardService.boardDelete(pw,pno);
    }

}
