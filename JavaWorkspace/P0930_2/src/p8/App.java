package p8;

public class App {
    public static void main(String[] args) {
        StringBuffer sbf = new StringBuffer();
        for(int i = 0; i < 100; i++){
            sbf.append("하오");

        }
        String st = sbf.toString();
        System.out.println(st);
        
        
        StringBuilder sbl = new StringBuilder();
        
        for(int i = 0; i < 100; i++){
            sbl.append("호나");
        }
        st = sbl.toString();
        System.out.println(st);
        // String str ="";
        // for(int i = 0; i < 100; i++){
        //     str += "야호";
        //     // str = str.concat("야호"); 새로운 인스턴스 생성 후 리턴 불변법칙 
        // }
        
        // System.out.println(str);
    }
}
