package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.StudentDto;
import jdbcpool.JdbcPool;

public class Repository {
    private JdbcPool jdbcPool;
    
    public Repository() {
    }

    public void setJdbc(JdbcPool jdbcPool){
        this.jdbcPool = jdbcPool;
    }
    
    public int insert(StudentDto dto){
        String query = "INSERT INTO StudentInfo(name, age, score) VALUES(?,?,?)";
        int result = 0;
        try {
            Connection connection = jdbcPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            
            statement.setString(1, dto.getName());
            statement.setInt(2, dto.getAge());
            statement.setInt(3, dto.getScore());

            result = statement.executeUpdate();
            return result;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }

    public List<StudentDto> findAll(){
        List<StudentDto> list = new ArrayList<>();

        try {
            String query = "SELECT name,age,score FROM StudentInfo";
            Connection connection = jdbcPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                int score = resultSet.getInt("score");

                StudentDto dto = new StudentDto(name,age,score);
                list.add(dto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }



    
    
}
