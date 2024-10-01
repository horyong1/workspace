package service;

import java.util.ArrayList;

import dto.StudentDto;
import ioutil.Ioutil;
import repository.Repository;

public class Service {
    private Repository repository = new Repository();

    public void addStudent(){
        String name = Ioutil.input("학생 이름 >>> ");
        int age = Integer.parseInt(Ioutil.input("학생 나이 >>> "));
        int score = Integer.parseInt(Ioutil.input("학생 나이 >>> "));
        StudentDto dto  = new StudentDto();
        repository.add(dto);
        Ioutil.print("학생 정보가 등록 되었습니다.");

    }

    public void showStudent(){
        int cnt  = 0;
        ArrayList<StudentDto> dtos = repository.show();
        for(StudentDto s : dtos){
            dtos.get(cnt++);
        }
    }
}
