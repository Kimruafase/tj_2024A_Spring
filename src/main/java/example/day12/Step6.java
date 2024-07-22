package example.day12;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Step6 {

    // HashMap 구현
    public static void main(String[] args) {
        HashMap<String,Integer> map = new HashMap<>();
        Scanner scan = new Scanner(System.in);

        while (true){
            System.out.println(" 1. 등록 2. 전체 출력 3. 수정 4. 삭제");
            int ch = scan.nextInt();
            if(ch == 1){
                System.out.print(" 이름 : ");
                String name = scan.next();
                System.out.print(" 나이 : ");
                int age = scan.nextInt();
                map.put(name,age);
            } else if (ch == 2) {
                Set<Map.Entry<String,Integer>> set = map.entrySet();
                System.out.println(set);
            } else if (ch == 3) {
                System.out.println("수정할 이름을 입력해주세요.");
                System.out.print(" 이름 : ");
                String name = scan.next();
                map.keySet().forEach(s->{
                    if(s.equals(name)) {
                        System.out.println("수정할 나이를 입력해주세요.");
                        System.out.print("나이 : ");
                        int age = scan.nextInt();
                        map.put(s, age);
                    }
                });
            } else if (ch == 4){
                System.out.println("삭제할 이름을 입력해주세요.");
                System.out.print(" 이름 : ");
                String name = scan.next();
                map.remove(name);
            }else{
                System.out.println(" 프로그램 종료 ");
                break;
            }
        }

    }


}
