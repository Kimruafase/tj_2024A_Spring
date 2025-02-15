package example.day09.lombok;

public class Step1 {
    public static void main(String[] args) {

        //  1. Member 객체 생성
        Member m1 = new Member();


        m1.setId("qwe");
        m1.setName("유재석");

        System.out.println(m1.getId());
        System.out.println(m1.getName());

        //  2. Member2 객체 생성
        Member m2 = new Member("asd", "강호동");
        System.out.println(m2);

        //  생성자는 규칙이 존재
            //  메소드와 동일하게 매개변수의 순서, 타입, 개수가 일치해야한다.
//        Member m3 = new Member();

        //  builder 패턴 생성 : 객체 생성 과정에서 표현 방법을 분리하여 다른 표현으로 객체 생성하는 방법
            //  필요한 데이터만 setting 가능, 유연성 확보 가능, 가독성을 높일 수 있다, 안정성을 보장한다.
        /*
            클래스명.builder().필드명(값).필드명(값).build();
        * */
        Member m3 = Member.builder().name("신동엽").id("zxc").build();

        System.out.println(m3);
    }
}
