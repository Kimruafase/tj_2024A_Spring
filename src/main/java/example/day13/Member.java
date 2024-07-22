package example.day13;

public class Member implements Comparable<Member>{
    String name;
    int age;

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    //  Comparable 인터페이스의 추상메소드 재정의
    @Override
    public int compareTo(Member o) {
        //  1. name 기준으로 정렬 -> String 클래스 내 CompareTo 활용
//        return this.name.compareTo(o.name);
        //  2. age 기준으로 정렬 -> int 는 기본타입 이므로 직접 정렬 기준을 비교해야함.
            //  -1 : 매개변수보다 더 작으면, 0 : 같으면, 1 : 매개변수보다 더 크면 (오름차순)
            //  -1 : 매개변수보다 더 크면, 0 : 같으면, 1 : 매개변수보다 더 작으면 (내림차순)
        if(this.age < o.age){
            return -1;
        }else if(this.age == o.age){
            return 0;
        }else return 1;
    }
}
