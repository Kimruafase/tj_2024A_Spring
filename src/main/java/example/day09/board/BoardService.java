package example.day09.board;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;

@Service
public class BoardService {

    @Autowired BoardDao boardDao;

    //  1. get
    public ArrayList<BoardDto> boardGet1(){
        ArrayList<BoardDto> list = boardDao.boardGet1();
        return list;
    }
    public BoardDto boardGet2(int pno){
        BoardDto boardDto = boardDao.boardGet2(pno);
        boardDao.viewUpdate(pno);
        return boardDto;
    }
    //  2. post
    public boolean boardPost(String title, String detail, String pw){
        return boardDao.boardPost(title, detail, pw);
    }
    //  3. put
    public boolean boardPut(String title, String detail, String pw, int pno){
        return boardDao.boardPut(title, detail, pw, pno);
    }

    //  4. delete
    public boolean boardDelete(String pw, int pno){
        return boardDao.boardDelete(pw, pno);
    }

}
