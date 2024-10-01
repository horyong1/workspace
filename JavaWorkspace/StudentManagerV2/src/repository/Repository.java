package repository;

import java.util.*;

import dto.StudentDto;

public class Repository {
    private List<StudentDto> students = new ArrayList<>();

    public int repositorySize(){
        return students.size();
    }

    public void add(StudentDto studentDto) {
        students.add(studentDto);
    }

    public List<StudentDto> show(){
        List<StudentDto> showStudents = new ArrayList<>();
        for(StudentDto s : students){
            showStudents.add(s);
        }
        return showStudents;
    }

    public List<StudentDto> search(String name){
        List<StudentDto> searchStudents = new ArrayList<>();
        for(StudentDto s : students){
            if(s.getName().contains(name)){
                searchStudents.add(s);
            }
        }
        return searchStudents;
    };

    public int[] searchCountList(String name){
        int count = 0;
        for(StudentDto s : students){
            if(s.getName().contains(name)){
                count++;
            }
        }

        int[] arr = new int[count];
        int index = 0;
        int cnt = 0;
        for(StudentDto s : students){
            if(s.getName().contains(name)){
                arr[index++] = cnt;
            }
            cnt++;
        }
        return arr;
    };

    public void remove(int num){
        students.remove(num);
    }

    public int remove(String name) {
        int result = 0;
        Iterator<StudentDto> iterator = students.iterator();
        while (iterator.hasNext()) {
            StudentDto s = iterator.next();
            if(s.getName().equals(name)){
                iterator.remove();
                result++;
            }
        }
        return result;
    }

    public double avg(){
        Iterator<StudentDto> iterator = students.iterator();
        double avg = 0;
        while (iterator.hasNext()) {
            StudentDto s = iterator.next();
            avg += s.getScore();
        }
        return Math.round((double)avg / repositorySize() * 100)/100.0;
    }
}
