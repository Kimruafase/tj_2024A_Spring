package example.day04;


import java.util.Arrays;

public class Step3 {
    public static void main(String[] args) {
        String money = "123123123";
        //  문제 : money 변수에 존재하는 문자열 금액의 천 단위 쉼표를 넣기
        StringBuilder commaMoney = new StringBuilder();
        int count = 0;
        for (int i = money.length()-1; i >= 0; i--) {
            commaMoney.insert(0,money.charAt(i));
            System.out.println(commaMoney);
            count++;
            if(count % 3 == 0 && i > 0){
                commaMoney.insert(0, ",");
            }
        }
        System.out.println(commaMoney);
    }
}
