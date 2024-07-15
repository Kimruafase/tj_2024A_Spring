package example.day08.board;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/board")
public class BoardController {

    //  1. get
    @GetMapping("/get1")
    public ArrayList<BoardDto> boardGet1(){
        ArrayList<BoardDto> list = BoardDao.getInstance().boardGet1();
        return list;
    }
    @GetMapping("/get2")
    public BoardDto boardGet2(int pno){
        BoardDto boardDto = new BoardDto();
        boardDto = BoardDao.getInstance().boardGet2(pno);
        BoardDao.getInstance().viewUpdate(pno);
        return boardDto;
    }
    //  2. post
    @PostMapping("/post")
    public boolean boardPost(String title, String detail, String pw){
        return BoardDao.getInstance().boardPost(title,detail,pw);
    }
    //  3. put
    @PutMapping("/put")
    public boolean boardPut(String title, String detail, String pw, int pno){
        return BoardDao.getInstance().boardPut(title,detail,pw,pno);
    }

    //  4. delete
    @DeleteMapping("/delete")
    public boolean boardDelete(String pw, int pno){
        return BoardDao.getInstance().boardDelete(pw,pno);
    }

}
