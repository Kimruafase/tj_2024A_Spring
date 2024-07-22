package example.day12;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

public class Step1 {
    public static void main(String[] args) {

        //  1. HashSet 선언
        Set<String> set = new HashSet<>();

        //  2. 대입
        set.add("임정순");
        set.add("박현정");
        set.add("홍연의");
        set.add("강감찬");
        set.add("강감찬");

        System.out.println(set);

        //  3. size()
        System.out.println(set.size());

        //  4. remove(object)
        set.remove("강감찬");
        System.out.println(set.size());

        System.out.println(set.remove("홍연의"));

        //  5. set Collection 순회 방법
            //  5-1. set 은 index 가 없으므로 .get(i) 사용 불가능
        Iterator<String> rs = set.iterator();
        while (rs.hasNext()){
            System.out.println(rs.next());
        }

        //  5-2. 향상된 for 문
        for (String s : set){
            System.out.println(s);
        }

        //  5-3. forEach -> 반환 값이 없다.
        set.forEach(s ->{
            System.out.println(s);
        });

        //  5-4. stream().map() -> 반환 값이 존재
        Set<String> newSet = set.stream().map(s -> {
            return s;
        }).collect(Collectors.toSet());
        System.out.println(newSet);

        //  6. 중복 제거 한다고 했는데, 만약 필드가 2개 이상인 객체라면?
        Set<Member> memberSet = new HashSet<>();

        memberSet.add(new Member(1001,"이지원"));
        memberSet.add(new Member(1002,"손민국"));
        memberSet.add(new Member(1003,"박서훤"));
        

        System.out.println("memberSet = " + memberSet);
        
        memberSet.add(new Member(1003,"박서훤"));
        System.out.println("memberSet = " + memberSet);
    }
}
