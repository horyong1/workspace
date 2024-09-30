package p1;

public class App {
    public static void main(String[] args) {
        // enum = 값의 범위를 제한(도메인 제한);
        // String gender = "F";
        // int gender = 0;
        // 0 = 남자
        // 1 = 여자
        Days days = Days.FRIDAY;


    }
}
enum Days{
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;
}

enum Gender{
    MALE("남자",0),
    FEMALE("여자",1);

    private String gender;
    private int value;

    Gender(String gender, int value){
        this.gender = gender;
        this.value = value;
    }

    public String getGender() {
        return gender;
    }

    public int getValue() {
        return value;
    }
    
}

