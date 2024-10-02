package p5;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<StudentDto> list = new ArrayList<>();
        list.add(new StudentDto("한조1", 1));
        list.add(new StudentDto("한조2", 2));
        list.add(new StudentDto("한조3", 3));
        list.add(new StudentDto("한조4", 4));
        list.add(new StudentDto("한조5", 5));
        list.add(new StudentDto("한조6", 6));

        // 다른 엔티티 배열 복사 방법
        List<StudentEntity> studentEntities = new ArrayList<>();
        for(StudentDto s : list){
            if(!s.name.startsWith("김")){
                continue;
            }
            StudentEntity se = new StudentEntity();
            se.name = s.name;
            se.age = s.age;
            studentEntities.add(se);
        }
        // 함수형으로 복사 하는 법 
        list.stream()
            .filter(e -> e.name.startsWith("김"))
            .map(e -> { 
                StudentEntity entity = new StudentEntity();
                entity.name = e.name;
                entity.age = e.age;
                return entity;
            })
            .collect(Collectors.toList())
            
        ;

        list.stream()
            .filter(e -> e.name.startsWith("김"))
            .map(StudentEntity::new)
            .collect(Collectors.toList())
        ;
    }
}

class AAA implements Function<StudentDto, StudentEntity>{

    @Override
    public StudentEntity apply(StudentDto t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'apply'");
    }

}



class StudentDto{
    String name;
    int age;
    
    public StudentDto() {

    }
    public StudentDto(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    

}

class StudentEntity{
    String name;
    int age;
    int score;
    public StudentEntity(){
        
    }
    public StudentEntity(StudentDto dto){
        this.name = dto.name;
        this.age = dto.age;
    }

    public StudentEntity(String name, int age) {
        this.name = name;
        this.age = age;
        
    }
}