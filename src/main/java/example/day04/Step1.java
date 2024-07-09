package example.day04;

public class Step1 {
    public static void main(String[] args) {

        //  1. 문자열 선언하는 방법 2가지
        String str1 = new String("abc");    //  [heap 영역] -> 상수 풀 참조
        String str2 = "test";                      //   상수 풀 [method 영역] -> 상수 풀 참조
        String str3 = "test";                      //   상수 풀 [method 영역] -> 상수 풀 참조
        String str4 = new String("abc");    //  [heap 영역] -> 상수 풀 참조

        System.out.println(str2 == str3);       //  true, 참조변수의 참조 주소가 같다.
        System.out.println(str1 == str4);       //  false, 참조변수의 참조 주소가 다르다.
        System.out.println(str1.equals(str4));  //  true
        System.out.println(str2.equals(str3));  //  true

        //  2. 두 문자열을 연결하는 방법
            //  2-1. 문자열1.concat(문자열2); : 두 문자열을 연결한 새로운 문자열을 반환하는 함수
        String javaStr = new String("java");
        String androidStr = new String("android");
        System.out.println(System.identityHashCode(javaStr));   //  연결 전 문자열 주소, 918221580

        javaStr = javaStr.concat(androidStr);
        System.out.println(javaStr);                            //  javaandroid
        System.out.println(System.identityHashCode(javaStr));   //  연결 후 문자열 주소, 2055281021

            //  2-2. 문자열1 += 문자열2; : 변수 = 변수 + 값
        String html1 = "<div>";
        String html2 = "하하</div>";
        System.out.println(System.identityHashCode(html1));     //  연결 전 문자열 주소, 1554547125

        html1 += html2;
        System.out.println(html1);                              //  <div>하하</div>
        System.out.println(System.identityHashCode(html1));     //  연결 후 문자열 주소, 603742814

            //  2-3. StringBuilder : 기존 메모리 문자열을 사용하는 문자열 연결 클래스, 메모리 효율성
        String javaStr2 = new String("java");
        String androidStr2 = new String("android");
        System.out.println(System.identityHashCode(javaStr2));  //  1067040082

        StringBuilder buffer = new StringBuilder(javaStr2);
        System.out.println("연결 후 1 : " + System.identityHashCode(buffer));  //  1325547227
        buffer.append(androidStr2);
        System.out.println("연결 후 2 : " + System.identityHashCode(buffer));  //  1325547227, 연결 전과 후가 주소값이 동일하다.
        javaStr2 = buffer.toString();
        System.out.println(javaStr2);                           //  javaandroid
        System.out.println(System.identityHashCode(javaStr2));  //  1325547227


    }
}
