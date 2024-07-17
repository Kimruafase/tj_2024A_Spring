package example.day10;

public class Step1 {
    public static void main(String[] args) {

        //  1. 제네릭 사용 X
        Box1 box1 = new Box1();
        box1.content = "안녕하세요";
        String content1 = box1.content;

        Box2 box2 = new Box2();
        box2.content = 100;
        int content2 = box2.content;

        //  2.
        Box3 box3 = new Box3();
        //  자동 타입 변환 String -> Object
        box3.content = "안녕하세요";
        //  강제 타입 변환 Object -> String
        String content3 = (String) box3.content;

        //  자동 타입 변환 int -> Object
        box3.content = 100;
        //  강제 타입 변환 Object -> int
        int content4 = (int)box3.content;

        //  3.
        Box4<String> box4 = new Box4<>();
        box4.content = "안녕하세요";
        String content5 = box4.content;

        Box4<Integer> box5 = new Box4<>();
        box5.content = 100;
        int content6 = box5.content;
    }
}
