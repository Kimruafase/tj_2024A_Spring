package example.day12;

import java.util.*;
import java.util.stream.Collectors;

public class Step2 {
    public static void main(String[] args) {


        //  1. Map 컬렉션 객체
        Map<String, Integer> map = new HashMap<>();

        //  2. put(key, value)
        map.put("유재석", 85);
        map.put("홍길동", 90);
        map.put("강호동", 100);
        map.put("신동엽", 90);     //  value 값은 중복이 가능하다.
        map.put("유재석", 78);     //  key 값이 중복이면 기존 key 제외

        System.out.println("map = " + map);
        
        //  3. size()
        int size = map.size();
        System.out.println("size = " + size);
        
        //  4. get(key)
        int point = map.get("강호동");
        System.out.println("point = " + point);

        //  5. remove(key)
        map.remove("강호동");

        //  6. keySet()
        Set<String> keys = map.keySet();
        System.out.println("keys = " + keys);

        //  7. values()
        Collection<Integer> values = map.values();
        System.out.println("values = " + values);

        //  8. entrySet()
        Set<Map.Entry<String,Integer>> entries = map.entrySet();
        System.out.println("entries = " + entries);

        //  9. map 객체 내 엔트리 순회
            //  9-1. Iterator
        Iterator<String> rs = map.keySet().iterator();
        while(rs.hasNext()){
            String key = rs.next();
            System.out.println(key);
            System.out.println(map.get(key));
        }
            //  9-2. 향상된 for 문
        for(String key : map.keySet()){
            System.out.println(key);
            System.out.println(map.get(key));
        }
        for(Integer value : map.values()){
            System.out.println("value = " + value);
        }

            //  9-3. forEach
        map.keySet().forEach(key ->{
            System.out.println(key);
            System.out.println(map.get(key));
        });

            //  9-4 stream().map().collect() 문
        map.keySet().stream().map(key ->{
            System.out.println(key);
            System.out.println(map.get(key));
            return key;
        }).collect(Collectors.toSet());
    }
}
