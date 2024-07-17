package example.day10;

public class Step4 {
    public static void main(String[] args) {

        List<String> list = new List<>();       // String 타입으로 객체1 생성
        System.out.println(list);

        list.add("x");      // 0번 인덱스
        list.add("y");      // 1번 인덱스
        list.add("z");      // 2번 인덱스
        list.add("w");      // 3번 인덱스
        list.add("a");      // 4번 인덱스
        list.add("b");      // 5번 인덱스
        list.add("c");      // 6번 인덱스
        System.out.println(list);

        list.remove(3); // 3번 인덱스 "w" 삭제
        System.out.println(list);

        System.out.println(list.get(3));          // 3번 인덱스 출력 -> a
        System.out.println(list.get(10));         // 10번 인덱스 출력 -> null

        List<Integer> list1 = new List<>();     // Integer 타입으로 객체2 생성
        System.out.println(list1);

        list1.add(1);       //  0번 인덱스
        list1.add(2);       //  1번 인덱스
        list1.add(3);       //  2번 인덱스
        list1.add(4);       //  3번 인덱스
        list1.add(5);       //  4번 인덱스
        list1.add(6);       //  5번 인덱스
        list1.add(7);       //  6번 인덱스
        System.out.println(list1);

        list1.remove(4);    // 4번 인덱스 int 5 삭제
        System.out.println(list1);

        System.out.println(list1.get(4));        //  4번 인덱스 출력 -> 6
        System.out.println(list1.get(10));       //  10번 인덱스 출력 -> null


    }
}
