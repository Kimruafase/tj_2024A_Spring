package example.day12;

import java.util.LinkedList;
import java.util.Scanner;

public class Step4 {

    //  LinkedList 구현
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LinkedList<User> list = new LinkedList<>();
        User user = null;

        while (true){
            System.out.println(" 1. 등록 2. 전체 출력 3. 수정 4. 삭제");
            int ch = scan.nextInt();
            if(ch == 1){
                System.out.print(" 이름 : ");
                String name = scan.next();
                System.out.print(" 나이 : ");
                int age = scan.nextInt();
                user = User.builder().age(age).name(name).build();
                list.add(user);
            } else if (ch == 2) {
                list.forEach(s ->{
                    System.out.println(s);
                });
            } else if (ch == 3) {
                System.out.println("리스트의 요소 갯수는 " + list.size() + "입니다.");
                System.out.println("인덱스는 0부터 " + (list.size() -1) + "까지 있습니다.");
                System.out.print(" 번호 입력 : ");
                int index = scan.nextInt();
                if(list.size() > index){
                    System.out.print(" 나이 입력 : ");
                    int age = scan.nextInt();
                    try{
                        list.set(index, User.builder().age(age).name(user.getName()).build());
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }else{
                    System.out.println("존재하지 않는 인덱스(번호)입니다.");
                }
            }else if(ch == 4){
                System.out.println("리스트의 요소 갯수는 " + list.size() + "입니다.");
                System.out.println("인덱스는 0부터 " + (list.size() -1) + "까지 있습니다.");
                System.out.print(" 번호 입력 : ");
                int index = scan.nextInt();
                if(list.size() > index){
                    list.remove(index);
                }else{
                    System.out.println("존재하지 않는 인덱스(번호)입니다.");
                }
            } else{
                System.out.println(" 프로그램 종료 ");
                break;
            }

        }

    }
}
