package example.day12;

import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public class Step5 {

    //  HashSet 구현
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        User user = null;
        HashSet<User> set = new HashSet<>();

        while (true){
            System.out.println(" 1. 등록 2. 전체 출력 3. 수정 4. 삭제");
            int ch = scan.nextInt();
            if(ch == 1){
                System.out.print(" 이름 : ");
                String name = scan.next();
                System.out.print(" 나이 : ");
                int age = scan.nextInt();
                user = User.builder().name(name).age(age).build();
                set.add(user);
            }else if(ch == 2){
                set.forEach(s ->{
                    System.out.println(s);
                });
            } else if (ch == 3) {
                System.out.println(" 수정할 이름을 입력해주세요. ");
                System.out.print(" 이름 : ");
                String name = scan.next();
                set.forEach(s ->{
                    if(s.name.equals(name)){
                        System.out.println("수정할 나이를 입력해주세요. ");
                        System.out.print(" 나이 : ");
                        int age = scan.nextInt();
                        s.age = age;
                    }
                });

            }else if (ch == 4){
                System.out.println(" 삭제할 이름을 입력해주세요.");
                System.out.print(" 이름 : ");
                String name = scan.next();
                set.forEach(s ->{
                    if(s.name.equals(name)){
                        set.remove(s);
                    }
                });
            }else{
                System.out.println(" 프로그램 종료 ");
                break;
            }
        }
    }
}
