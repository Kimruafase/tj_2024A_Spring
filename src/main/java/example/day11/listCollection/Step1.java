package example.day11.listCollection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class Step1 {

    public static void main(String[] args) {


        //  1. list Collection
        List<String> list = new ArrayList<>();
//        ArrayList<String> list2 = new ArrayList<>();


        //  2. 리스트 내 요소 추가
        list.add("유재석");
        list.add("강호동");
        list.add("신동엽");
        list.add("서장훈");
        System.out.println("list = " + list);
        
        //  3. 리스트 내 특정 위치에 요소 추가
        list.set(0,"박명수");
        list.set(3,"김희철");

        System.out.println("list = " + list);
        
        //  4. 리스트의 요소 갯수 반환
        int size = list.size();
        System.out.println("size = " + size);
        
        //  5. 리스트 내 특정 위치 요소 값 반환
        String str1 = list.get(1);
        System.out.println("str1 = " + str1);
        
        //  6. 리스트 내 특정 요소의 값 검색
        boolean bool1 = list.contains("강호동");
        System.out.println("bool1 = " + bool1);

        //  7. 리스트 내 특정 요소의 값 검색 후 인덱스 반환
        int index1 = list.indexOf("강호동");
        System.out.println("index1 = " + index1);

        //  8. 리스트 내 특정 인덱스 or 객체 삭제
        list.remove(1);
        System.out.println("list = " + list);

        //  9. 리스트 내 요소 순회
        //  9-1. 일반 for 문
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        //  9-2. 향상된 for 문
        for(String s : list){
            System.out.println(s);
        }

        //  9-3. forEach method -> 요소의 갯수를 하나씩 반환해서 반복 후 출력 -> return 없음
        list.forEach(s ->{
            System.out.println(s);
        });

        //  9-4. .stream().map().collect() -> 요소의 갯수를 하나씩 반환해서 반복 후 결과를 return 가능
        //  반복하면서 return 값들을 하나의 배열 / 컬렉션으로 반환 받을 수 있다.
        //  주로 카피 / 복사할 때 사용된다.
        List<String> newList = list.stream().map(s ->{
            System.out.println(s);return s;
        }).collect(Collectors.toList());
        System.out.println("newList = " + newList);

        newList.remove(0);
        System.out.println(list);
        System.out.println(newList);
        
        //  vector
        List<String> vector = new Vector<>();
        vector.add("유재석");
        System.out.println("vector = " + vector);

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new LinkedList<>();

        long startTime;
        long endTime;
        long result;

        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            list1.add(String.valueOf(i));
        }
        endTime = System.nanoTime();
        result = endTime - startTime;
        System.out.println("1만개를 저장하는데 걸린 시간 : " + result);


        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            list2.add(String.valueOf(i));
        }
        endTime = System.nanoTime();
        result = endTime - startTime;
        System.out.println("1만개를 저장하는 데 걸린 시간 : " + result);
    }
}
