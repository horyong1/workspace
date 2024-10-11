package service;


import java.util.List;

import dto.StudentDto;
import ioutil.Ioutil;
import repository.Repository;

public class Service {
  
    private Repository repository = new Repository();

    /**
     * 학생 정보 등록
     */
    public void addStudent(){
        String name = Ioutil.input("학생이름 : ");
        int age = Integer.parseInt(Ioutil.input("학생나이 : "));
        int score =Integer.parseInt(Ioutil.input("학생점수 : "));
        StudentDto dto = new StudentDto(name,age,score);
        
        int result = repository.insert(dto);
        
        if(result > 0){
            Ioutil.print("학생정보가 등록되었습니다.");
        }else{
            Ioutil.print("학생정보 등록 중 오류가 발생하였습니다.");
        }

    }

    /**
     * 학생 전체 정보 출력
     */
    public void findAllStudent(){
        List<StudentDto> list = repository.findAll();
        studentListShow(list);
    }

    /*
     * 학생 정보 검색
     */
    public void findByNameStudent(){
        List<StudentDto> list = searchNameStudentList();
        studentListShow(list);
    }
    
    /*
     * 학생 정보 삭제
     */
    public void removeStudent(){
        List<StudentDto> list = searchNameStudentList();
        studentListShow(list);

        if(list.size() == 0){
            Ioutil.print("해당 학생이 존재하지 않습니다.");
            return;
        }

        // 학생 삭제 여부 확인
        if (!confirmDeletion(list)) return;

         // 삭제할 학생 선택
        StudentDto dto = (list.size() > 1) ? getStudentToRemove(list) : list.get(0);
        int result = repository.remove(dto);
    
        removeResultPrint(result);
    }


    /*
     * 학생 평균 점수 통계
     */
    public void avgScore(){
        List<StudentDto> list = repository.findAll();
        int sum = 0;
        double avg = 0;
        for(StudentDto s : list){
            sum += s.getScore();
        }
        avg = Math.round(((double)sum/list.size()) * 100) /100.0;
        Ioutil.print("====================================");
        Ioutil.print("학생 평균 점수는 " + avg + "점 입니다.");
        Ioutil.print("====================================");
    }

    private List<StudentDto> searchNameStudentList(){
        String name = Ioutil.input("검색 학생 이름 입력 >>> ");
        List<StudentDto> list = repository.findByName(name);
        return list;
    }

    private void studentListShow(List<StudentDto> list){
        int cnt = 1;
        Ioutil.print("===========================");
        
        if(list.isEmpty()){
            Ioutil.print("학생 정보가 없습니다.");
            return;
        }

        for(StudentDto s : list){
            Ioutil.print("번호 : " + cnt++);
            Ioutil.print("학생이름 : " + s.getName());
            Ioutil.print("학생나이 : " + s.getAge());
            Ioutil.print("학생점수 : " + s.getScore());
            Ioutil.print("===========================");
        }
    }

    // 학생 삭제 여부 확인 메서드
    private boolean confirmDeletion(List<StudentDto> list) {
        if (list.size() > 1) {
            Ioutil.print(list.size() + "명이 검색되었습니다.");
        }
        String yn = Ioutil.input("해당 학생을 삭제 하시겠습니까? (y/n) >>> ");
        return !(yn.equalsIgnoreCase("n")); // 'n' 또는 'N'일 경우 false 반환
}

    // 삭제할 학생 선택 메서드
    private StudentDto getStudentToRemove(List<StudentDto> list) {
        int num = Integer.parseInt(Ioutil.input("삭제하려는 학생 번호를 입력해 주세요 >>> "));
        return list.get(num - 1);
    }

    private void removeResultPrint(int result){
        if(result == 1){
            Ioutil.print("삭제가 완료되었습니다.");
        }else{
            Ioutil.print("삭제 중 오류 발생");
        }
    }

}
