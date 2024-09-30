package p5;

import java.io.File;

public class App {
    public static void main(String[] args) {
        // 예외처리 정리
        // 예외처리는 사실상 시스템 안정성에 매우 중요한 문제
        // 예외처리 구문은 필요에 따라 구조 설계하는데도 중요함
        // 예외처리는 대부분의 경우 코드 가독성을 심각하게 저해한다.
        // 예측불가능한 예외가 너무 많기 때문에 모든 예외처리를 한다는 것은 현실적으로 불가능

        // 예외처리를 내가 대충 넘어간 이유
        // 정상적으로 만드는 것 자체가 어려움

        // 다만 알아둬야 할 것
        // 어떤 APU는 내부에서 Exceptiond을 throw한다.
        // throw exception이 checked 예외인 경우에는 필수적으로 try-catch를 해줘야 함
        // 예) 네트워크, 파일 입출력과 관련된 API

       File file = new File("text");
    

    }
}

