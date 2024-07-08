package example.day03;

public class Step2 {
    public static void main(String[] args) {

        //  1. 2개의 문자열 객체 생성
        String str1 = new String("abc");    //  str1 -> 302번지( 502번지 )
        String str2 = new String("abc");    //  str2 -> 402번지( 502번지 )

        String str3 = "abc";                       //  str3 -> 502번지

        int i1 = 100;
        int i2 = 100;
        Integer i3 = 100;                           //  int 기본 타입은 Object 의 메소드를 사용할 수 없으므로 int 타입도 사용할 수 있게 하기 위해 만들어진 참조 타입 (Wrapper 클래스)

        //  2.
        System.out.println(str1.hashCode());        //  96354, String 타입의 객체 같은 경우 문자열의 저장 위치를 참조
        System.out.println(str2.hashCode());        //  96354
        System.out.println(str3.hashCode());        //  96354, * 2개의 객체의 멤버변수와 "abc"는 동일한 저장 위치를 참조한다.

        System.out.println(str1 == str2);           //  false   //  302번지 == 402번지, false
        System.out.println(str1.equals(str2));      //  true    //  302번지.equals(402번지), true

        System.out.println(str1 == str3);           //  false   //  302번지 == 502번지, false
        System.out.println(str1.equals(str3));      //  true    //  302번지.equals(502번지), true

        System.out.println(i1 == i2);               //  true
        System.out.println(i1 == i3);               //  true
//        System.out.println(i1.equals);            //  int 는 참조타입이 아니므로 Object 클래스로부터 상속받지 못한다.
        System.out.println(i3.equals(i1));          //  true, Integer 는 참조타입이므로 Object 클래스로부터 상속 받아 equals()를 사용 가능하다.

//        Integer.parseInt()
        //  Integer.parseInt() vs parseInt() (JS) : 문자열 타입을 정수 타입으로 변환, "10"과 10은 다르기 때문에 변환해줘야 함.


        //  clone
//        str1.clone();   // 기본적으로 clone() 메소드 사용이 불가능
//        Object object = new Object();
//        object.clone(); // 기본적으로 clone() 메소드 사용이 불가능


        /*
            [1]
                stack 메모리                   heap 메모리

                str1                          new String() : 302번지 주소값을 가지는 객체 생성
                str2                          new String() : 402번지 주소값을 가지는 객체 생성

                1. '=='     : 다르다, 302번지와 402번지 주소값을 가지는 두 지역 변수는 다른 것이 맞다.
                2. equals() : 302번지와 402번지가 가지는 객체를 비교했더니 같다고 출력.

            [2]
                method 메모리              stack 메모리                   heap 메모리

                리터럴                       str1(302번지 참조)            new String() : 302번지 주소값을 가지는 객체 생성
                                                                                멤버변수 : "abc" , 502번지 주소값을 가지는 객체
                                            str2(402번지 참조)            new String() : 402번지 주소값을 가지는 객체 생성
                                                                                멤버변수 : "abc" , 502번지 주소값을 가지는 객체
                                            str3(502번지 참조)
                                                                                - "abc" : 502번지 주소값을 가지는 객체 생성
                *리터럴(Literal) : 1, 3.15, true, '0'

        * */




    }
}
