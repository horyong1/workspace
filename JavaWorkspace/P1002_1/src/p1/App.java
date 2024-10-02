package p1;

import java.util.HashMap;
import java.util.Map;

/**
 * Dto VS Map 
 * Dto 장점 : 구조가 명확해 코드 가독성이 좋다 
 *     단점 : 클래스가 많아지면 관리가 힘들다 
 *            데이터 추가 또는 수정이 일어나면 전체적인 코드 수정이 
 *            필요 할 수도 있다.
 * Map 장점 : 데이터구조가 동적으로 변할수 있어 유연하다.
 *     단점 : 데이터 구조가 보이지 않아 가독성이 저하 
 *           데이터 유효성을 검증하기 어려워 데이터 무결성이 떨어짐
 */
public class App {
    public static void main(String[] args) {
        Map<String,Object> map = new Test().method();
        System.out.println(map.get("b"));

        int aaa = (int)map.get("a");
        String bbb = (String)map.get("b");

        System.out.println(aaa);
        System.out.println(bbb);
    }
}


class Test{
    Map<String, Object> method(){
        int a = 0;
        String b = "pppp" + a;
        Map<String, Object> map = new HashMap<>();
        map.put("a", a);
        map.put("b", b);

        return map;
    }
}