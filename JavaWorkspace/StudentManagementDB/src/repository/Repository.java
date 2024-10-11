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
        try {
            this.jdbcPool = JdbcPool.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 학생 정보 입력
     */
    public int insert(StudentDto dto){
        String query = "INSERT INTO StudentInfo(name, age, score) VALUES(?,?,?)";
        int result = 0;
        Connection connection = null;
        try {
            connection = jdbcPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            
            statement.setString(1, dto.getName());
            statement.setInt(2, dto.getAge());
            statement.setInt(3, dto.getScore());

            result = statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                jdbcPool.releaseConnection(connection); // 커넥션을 반환
            }
        }
        
        return result;
    }

    /**
     * 학생 정보 전체 출력
     */
    public List<StudentDto> findAll(){
        List<StudentDto> list = new ArrayList<>();
        Connection connection = null;
        String query = "SELECT * FROM StudentInfo";

        try {
            connection = jdbcPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                int no = resultSet.getInt("no");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                int score = resultSet.getInt("score");

                StudentDto dto = new StudentDto(no,name,age,score);
                list.add(dto);
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            if (connection != null) {
                jdbcPool.releaseConnection(connection); // 커넥션을 반환
            }
        }


        return list;
    }
    
    /**
     * 학생 정보 검색
     */
    public List<StudentDto> findByName(String name){
        List<StudentDto> list = new ArrayList<>();
        Connection connection = null;
        String query = "SELECT * FROM StudentInfo WHERE name LIKE ?";

        try {
            connection = jdbcPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, "%" + name + "%");
            
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                int no = resultSet.getInt("no");
                String name1 = resultSet.getString("name");
                int age = resultSet.getInt("age");
                int score = resultSet.getInt("score");

                StudentDto dto = new StudentDto(no, name1, age, score);
                list.add(dto);
            }
            resultSet.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                jdbcPool.releaseConnection(connection); // 커넥션을 반환
            }
        }

        return list;
    }

    /**
     * 학생 정보 삭제
     */
    public int remove(StudentDto dto){
        String query = "DELETE FROM StudentInfo WHERE no=? AND name=? AND age=? AND score=?";
        int result = 0;
        Connection connection = null;
        try {
            connection = jdbcPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, dto.getNo());
            statement.setString(2, dto.getName());
            statement.setInt(3, dto.getAge());
            statement.setInt(4, dto.getScore());

            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                jdbcPool.releaseConnection(connection); // 커넥션을 반환
            }
        }

        return result;
    }
}
