package repository;

import java.util.ArrayList;

import dto.StudentDto;

public class Repository {
    private ArrayList<StudentDto> students = new ArrayList<>();

    public void add(StudentDto studentDto) {
        students.add(studentDto);
    }

    public ArrayList<StudentDto> show(){
        ArrayList<StudentDto> showStudents = new ArrayList<>();
        for(StudentDto s : students){
            showStudents.add(s);
        }
        return showStudents;
    }
}
