package example.day07.restcontroller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
public class RestController5 {

    // ============= HTTP BODY 매개변수들을 매핑하는 방법 ======================== //

    //  0. HTTP POST (쿼리스트링 가능)
    @PostMapping("/rest5/test1")
    public String test1(String key1, int key2){
        System.out.println("RestController5.test1");
        System.out.println("key1 = " + key1 + ", key2 = " + key2);
        return "test1 HI";
    }

    //  1. HTTP POST
    @PostMapping("/rest5/test2")
    public String test2(@RequestBody RestDto restDto){
        System.out.println("RestController5.test2");
//        System.out.println("key1 = " + key1 + ", key2 = " + key2);
        System.out.println("restDto = " + restDto);

        return "test2 HI";
    }




}
