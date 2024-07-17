package example.day10;

import java.util.Arrays;

public class List <T> {

    private int size = 0;
    private T[] array = (T[]) new Object[size];

    public void add(T x){
        size++;                                     //  길이를 나타내는 size 변수를 + 1
        T[] newArray = (T[]) new Object[size];      //  기존 배열보다 길이 + 1인 새로운 배열 생성
        for (int i = 0; i < array.length; i++) {    //  기존 배열의 길이만큼 반복문을 돌려서 새로운 배열에 기존 배열의 값들을 대입
            newArray[i] = array[i];
        }
        newArray[newArray.length - 1] = x;          //  새로운 배열의 마지막 인덱스에 매개변수로 받은 값을 대입
        array = newArray;                           //  새로운 배열을 기존 배열에 재대입
    }

    public void remove(int index){
        for (int i = 0; i < array.length; i++) {            //  기존 배열의 길이만큼 반복문을 돌려서 매개변수로 받은 인덱스와 같은 값이 존재하면
            if(i == index){
                for (int j = i; j < array.length-1; j++) {  //  i번째 인덱스부터 기존 배열의 길이 - 1 까지 반복문을 다시 돌려서
                    array[j] = array[j+1];                  //  기존 배열의 j번째 인덱스에 j + 1번째 인덱스의 값 대입
                }
            }
        }
        size--;                                             //  길이를 나타내는 변수인 size 를 - 1
        T[] newArray = (T[]) new Object[size];              //  기존 배열보다 길이 - 1인 새로운 배열 생성
        for (int i = 0; i < newArray.length; i++) {         //  새로운 배열의 길이만큼 반복문을 돌려서 새로운 배열에 기존 배열의 값들을 대입
            newArray[i] = array[i];
        }
        array = newArray;                                   //  새로운 배열을 기존 배열에 재대입
    }

    public T get(int index){
            if(index > array.length) {                      //  입력받은 값이 만약 배열의 길이보다 긴 값이라면 배열에 없으므로 null 반환
                return null;
            }
        return array[index];                                //  입력받은 값의 인덱스 반환
    }

    @Override
    public String toString() {
        return "List{" +
                "size=" + size +
                ", array=" + Arrays.toString(array) +
                '}';
    }
}
