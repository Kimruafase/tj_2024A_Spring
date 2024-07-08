package example.day03;

class Book{
    int bookNumber;
    String bookTitle;

    public Book(int bookNumber, String bookTitle) {
        this.bookNumber = bookNumber;
        this.bookTitle = bookTitle;
    }   // 생성자 end

    //  메소드
    public String toString(){
        return "Object Info : " + bookTitle + ", " + bookNumber;
    }
}   // class end

public class Step1 {
    public static void main(String[] args) {
        //  1. 객체 생성
        Book book1 = new Book(200,"개미");       // ex_ stack 메모리에 book1 변수가 heap 메모리에 302번지에 생성한 객체를 참조

        //  2. Book 클래스의 메소드가 아닌 Object 클래스의 toString() 메소드 호출
        System.out.println(book1);                  // toString()을 입력하지 않아도 참조변수를 출력하면 자동으로 호출됨. example.day03.Book@5ca881b5
        System.out.println(book1.toString());       // example.day03.Book@5ca881b5

        //  3. 객체 2 생성
        Book book2 = new Book(300,"호랑이");     // ex_ stack 메모리에 book2 변수가 heap 메모리에 402번지에 생성한 객체를 참조

        //  4. 객체 3 생성
        Book book3 = new Book(200,"개미");       // 객체 1 멤버변수와 동일하게 생성   ex_ stack 메모리에 book3 변수가 heap 메모리에 502번지에 생성한 객체를 참조

        //  5. 객체 4 생성이 아닌 객체를 참조하여 대입
        Book book4 = book1;                                         // stack 메모리에 book4 변수가 heap 메모리에 302번지에 생성한 객체를 참조

        //  비교
        System.out.println(book1 == book2);         // false        ex_ 302번지 주소와 402번지 주소가 같냐고 물어보는 것
        System.out.println(book1 == book3);         // false        ex_ 302번지 주소와 502번지 주소가 같냐고 물어보는 것

        System.out.println(book1.equals(book2));    // false        ex_ 302번지.equals(402번지)
        System.out.println(book1.equals(book3));    // false        ex_ 302번지.equals(502번지)

        System.out.println(book1 == book4);         // true         ex_ 302번지 주소와 302번지 주소가 같냐고 물어보는 것
        System.out.println(book1.equals(book4));    // true         ex_ 302번지.equals(302번지)


    }
}
