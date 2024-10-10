package p4;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class dbconnection {
    private static final String URL = "jdbc:mariadb://localhost:3306/test"; // 데이터베이스 주소
    private static final String USER = "scott"; // MariaDB 사용자명
    private static final String PASSWORD = "1234"; 
    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Class.forName("org.mariadb.jdbc.Driver");


            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("DB 연결 성공 !");

            String query = "SELECT * FROM Orders";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("orderid : " + resultSet.getInt("orderid"));
                System.out.println("custid : " + resultSet.getInt("custid"));
                System.out.println("bookid : " + resultSet.getInt("bookid"));
                System.out.println("saleprice : " + resultSet.getInt("saleprice"));
                String s = String.valueOf(resultSet.getTimestamp("orderdate"));
                System.out.println("orderdate : " + s);
                System.out.println("-----------------------------");
            }

            resultSet.close();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                    System.out.println("MariaDB 연결 해제 성공!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        

    }
}
