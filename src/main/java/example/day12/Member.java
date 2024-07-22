package example.day12;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor     //  해당 클래스의 풀 생성자를 자동으로 주입 / 생성
@NoArgsConstructor      //  해당 클래스의 빈 / 기본 생성자를 자동으로 주입 / 생성
@ToString               //  해당 클레스의 toString 메소드를 자동으로 재정의함

public class Member {
    private int memberId;
    private String memberName;


    //  중복 기준 정의, 회원 번호로 중복 제거
    @Override
    public int hashCode() {
        return memberId;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Member){
            Member member = (Member) obj;
            if(this.memberId == member.memberId){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
}
