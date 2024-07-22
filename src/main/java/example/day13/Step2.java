package example.day13;

import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Step2 {
    public static void main(String[] args) {

        //  1. 이진 트리
        TreeSet<Integer> scores = new TreeSet<>();
            //  SET 인터페이스에는 Tree 자료구조에 메소드가 존재 X

        //  2. add()
        scores.add(23);
        scores.add(10);
        scores.add(48);
        scores.add(15);
        scores.add(7);
        scores.add(22);
        scores.add(56);
        System.out.println("scores = " + scores);

        System.out.println();

        //  3. 리스트 순회
        scores.forEach(s ->{
            System.out.println("s = " + s);
        });

        System.out.println();

        //  4. 트리 관련 메소드 제공
        System.out.println("가장 낮은 점수, 가장 왼쪽 데이터 = " + scores.first());
        System.out.println("가장 높은 점수, 가장 오른쪽 데이터 = " + scores.last());
        System.out.println("48보다 아래 점수 출력 = " + scores.lower(48));
        System.out.println("48보다 위인 점수 출력 = " + scores.higher(48));
        System.out.println("48이거나 아래인 점수 출력 = " + scores.floor(48));
        System.out.println("48이거나 위인 점수 출력 = " + scores.ceiling(48));
        System.out.println("내림차순으로 반환 = " + scores.descendingSet());
        System.out.println("48보다 이상 = " + scores.tailSet(48,true));   //  true : 이상, false : 초과
        System.out.println("48보다 이하 = " + scores.headSet(48,true));     //  true : 이하, false : 미만
        System.out.println("10 이상 48 미만 = " + scores.subSet(10,true,48,false));

        System.out.println();
        
        //  5. TreeMap
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        
        //  6. put()
        treeMap.put(23,"apple");
        treeMap.put(10,"forever");
        treeMap.put(48,"ever");
        treeMap.put(15,"base");
        treeMap.put(7,"cherry");
        treeMap.put(22,"guess");
        treeMap.put(56,"zoo");
        System.out.println("treeMap = " + treeMap);

        System.out.println();
        
        //  7. 리스트 순회
        treeMap.entrySet().forEach(entry->{
            System.out.println("entry = " + entry);
        });

        System.out.println();

        //  8. 여러가지 메소드
        System.out.println("첫번째 엔트리 = " + treeMap.firstEntry());
        System.out.println("마지막 엔트리 = " + treeMap.lastEntry());
        System.out.println("키가 48보다 아래인 엔트리 = " + treeMap.lowerEntry(48));
        System.out.println("키가 48보다 위인 엔트리 = " + treeMap.higherEntry(48));
        System.out.println("키가 48이거나 아래인 엔트리 = " + treeMap.floorEntry(48));
        System.out.println("키가 48이거나 위인 엔트리 = " + treeMap.ceilingEntry(48));
        System.out.println("내림차순으로 반환 = " + treeMap.descendingMap());
        System.out.println("48보다 이상 = " + treeMap.tailMap(48,true));
        System.out.println("48보다 이하 = " + treeMap.headMap(48,true));
        System.out.println("10 이상 48 미만 = " + treeMap.subMap(10,true,48,false));


    }
}
