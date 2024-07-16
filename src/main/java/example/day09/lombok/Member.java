package example.day09.lombok;


import lombok.*;

@AllArgsConstructor     //  해당 클래스의 풀 생성자를 자동으로 주입 / 생성
@NoArgsConstructor      //  해당 클래스의 빈 / 기본 생성자를 자동으로 주입 / 생성
@ToString               //  해당 클레스의 toString 메소드를 자동으로 재정의함
@Setter                 //  해당 클래스에 Setter 메소드를 자동으로 주입 / 생성
@Getter                 //  해당 클래스에 getter 메소드를 자동으로 주입 / 생성
@Builder                //  해당 클래스에 builder 패턴 생성
public class Member {

    //  필드
    private String id;
    private String name;
//    private String address;
    //  필드 변화에 따라 유동적으로 getter, setter, 생성자, toString 을 사용할 수 있다.


//    public Member(String id, String name){
//
//    }

//    public Member(String id, String address){
//
//    }
}
