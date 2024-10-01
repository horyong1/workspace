package service;

import java.util.*;

import dto.StudentDto;
import ioutil.Ioutil;
import repository.Repository;

public class Service {
    private Repository repository = new Repository();

    public void addStudent(){
        Ioutil.print("[학생 정보 입력]");
        DataAdd();
        Ioutil.print("학생 정보가 등록 되었습니다.");

    }

    public void showStudent(){
        List<StudentDto> dtos = repository.show();
        studentListShow(dtos);
    }

    public void searchStudent(){
        List<StudentDto> dtos = new ArrayList<>();
        dtos = repository.search(Ioutil.input("검색할 학생 이름 >>> "));
        studentListShow(dtos);
    }

    public void removeStudent(){
        int removeCount = 0;
        String name = Ioutil.input("삭제할 학생 이름 >>> ");
        List<StudentDto> dtos = repository.search(name);
        int[] arr = repository.searchCountList(name);
        
        removeCount = selectRemove(arr, dtos, name);

        if(removeCount > 0){
            Ioutil.print("학생 " + removeCount + "명을 삭제하였습니다.");
        }else{
            Ioutil.print("해당 학생이 없습니다.");
        }
    }

    public void avgScore(){
        double avg = repository.avg();
        Ioutil.print(String.valueOf(avg));
    }


    private void DataAdd(){
        StudentDto dto  = new StudentDto();
        String key = Ioutil.input("테스트 데이터를 넣겠습니까?(Y/n) >>>> ");
        if(key.equals("Y") || key.equals("y")){
            testData();
        }else{
            dto = studentInfo();
            repository.add(dto);
        }
    }

    private StudentDto  studentInfo(){
        String name = Ioutil.input("학생 이름 >>> ");
        int age = Integer.parseInt(Ioutil.input("학생 나이 >>> "));
        int score = Integer.parseInt(Ioutil.input("학생 나이 >>> "));
        return new StudentDto(name,age,score);
    }

    private void testData(){
        String[] name = {"홍길동","김철수","가가멜","이영희","홍길동","신짱구"};
        int[] age = {14,54,22,34,55,66};
        int[] score = {22,33,11,23,42,44};
        for(int i = 0; i < name.length; i++){
            repository.add(new StudentDto(name[i],age[i],score[i]));
        }
    }

    private void studentListShow(List<StudentDto> dtos){
        int count = 1;
        Ioutil.print("========================");
        for(StudentDto s : dtos){
            Ioutil.print("번호 : " + count++);
            Ioutil.print("학생이름 : " + s.getName());
            Ioutil.print("학생나이 : " + s.getAge());
            Ioutil.print("학생점수 : " + s.getScore());
            Ioutil.print("========================");
        }
    }

    private int selectRemove(int[] arr, List<StudentDto> dtos, String name){
        int removeCount = 0;
        if(arr.length > 1){
            Ioutil.print(dtos.size() + "명의 학생이 검색 되었습니다.");
            studentListShow(dtos);
            int num = Integer.parseInt(Ioutil.input("삭제할 학생 번호를 입력해주세요 >>> "));
            
            repository.remove(arr[num-1]);
           removeCount++;
         }else{
            removeCount = repository.remove(name);
         }
         return removeCount;
    }
}
