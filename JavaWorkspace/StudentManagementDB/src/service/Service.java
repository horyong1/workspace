package service;

import java.sql.SQLException;
import java.util.List;

import dto.StudentDto;
import ioutil.Ioutil;
import jdbcpool.JdbcPool;
import repository.Repository;

public class Service {
  
    private Repository repository = new Repository();

    public void addStudent(){
        String name = Ioutil.input("학생이름 : ");
        int age = Integer.parseInt(Ioutil.input("학생나이 : "));
        int score =Integer.parseInt(Ioutil.input("학생점수 : "));
        StudentDto dto = new StudentDto(name,age,score);
        
        try {
            JdbcPool jdbcPool = JdbcPool.create();
            repository.setJdbc(jdbcPool);
            int result = repository.insert(dto);
          
            if(result > 0){
                Ioutil.print("학생정보가 등록되었습니다.");
            }else{
                Ioutil.print("학생정보 등록 중 오류가 발생하였습니다.");
            }
            
            jdbcPool.shutdown();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            
        }


    }

    public void findAllStudent(){
        try {
            JdbcPool jdbcPool = JdbcPool.create();
            repository.setJdbc(jdbcPool);
            List<StudentDto> list = repository.findAll();
            
            Ioutil.print("===========================");
            for(StudentDto s : list){
                Ioutil.print("학생이름 : " + s.getName());
                Ioutil.print("학생나이 : " + s.getAge());
                Ioutil.print("학생점수 : " + s.getScore());
                Ioutil.print("===========================");
            }
            jdbcPool.shutdown();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
