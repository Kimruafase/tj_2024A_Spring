package example.day07.restcontroller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class RestController2 {

    //  1. HTTP GET
    @RequestMapping(value = "/example/rest2",method = RequestMethod.GET)
    @ResponseBody       // 응답할 데이터의 JAVA 타입을 HTTP 타입으로 자동 설정 : (JAVA) String -> (HTTP) text / plain
    public String getRest2(HttpServletRequest request){
        System.out.println("RestController2.getRest2");

        //  1) 요청
        String key = request.getParameter("key");
        System.out.println("key = " + key);

        //  2) 응답
            //  2-1) 메소드의 반환 타입 정의
        return "[GET] Client HI";
            //  2-2) @ResponseBody 어노테이션 정의
    }

    //  2. HTTP POST
    @RequestMapping(value = "/example/rest2",method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<RestDto> getPost(HttpServletRequest request){
        System.out.println("RestController2.getPost");

        //  1) 요청
        String key = request.getParameter("key");
        System.out.println("key = " + key);

        //  2-1) 배열 타입 -> JSON : ["[POST]","Client HI"]
//        String[] strArray = new String[2];
//        strArray[0] = "[POST]";
//        strArray[1] = "Client HI";

        //  2-2) ArrayList String 타입 -> JSON : ["[POST]","Client HI"]
//        ArrayList<String > strArray = new ArrayList<>();
//        strArray.add("[POST]");
//        strArray.add("Client HI");

        //  2-3) DTO 타입 -> JSON : {key1: "[POST]", key2: "Client HI"}
//        RestDto restDto = new RestDto("[POST]", "Client HI");

        //  2-4) ArrayList DTO 타입 -> JSON : [{"key1": "[POST]", "key2": "Client HI" }, {"key1": "[POST]", "key2" : "Client HI"}]
        ArrayList<RestDto> restDto = new ArrayList<>();
        restDto.add(new RestDto("[POST]","Client HI"));
        restDto.add(new RestDto("[POST]","Client HI"));
        return restDto;        // JSON : ["[POST]","Client HI"]
    }

    //  3. HTTP PUT
    @RequestMapping(value = "/example/rest2",method = RequestMethod.PUT)
    @ResponseBody
    public int putRest2(HttpServletRequest request){
        System.out.println("RestController2.putRest2");

        //  1) 요청
        String key = request.getParameter("key");
        System.out.println("key = " + key);

        //  2) 응답
        return 10+10;
    }

    //  4. HTTP DELETE
    @RequestMapping(value = "/example/rest2",method = RequestMethod.DELETE)
    @ResponseBody
    public boolean putDelete2(HttpServletRequest request){
        System.out.println("RestController2.putDelete2");

        //  1) 요청
        String key = request.getParameter("key");
        System.out.println("key = " + key);

        //  2) 응답
        return true;
    }

}
