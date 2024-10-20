package dto;

public class StudentDto {
    private int no;
    private String name;
    private int age;
    private int score;

    public StudentDto(String name, int age, int score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public StudentDto(int no, String name, int age, int score) {
        this.no = no;
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public StudentDto() {
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public int getNo() {
        return no;
    }
    
}
