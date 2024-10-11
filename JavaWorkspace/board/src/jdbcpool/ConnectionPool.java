package jdbcpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ioutil.Ioutil;

public class ConnectionPool {
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static final int INITIAL_POOL_SIZE = 5;

    private static final String URL = "jdbc:mariadb://localhost:3306/test";
    private static final String USER = "scott";
    private static final String PASSWORD = "1234";

    private static ConnectionPool instance;

    public ConnectionPool() throws SQLException{
        connectionPool = new ArrayList<>(INITIAL_POOL_SIZE);
        for(int i = 0; i < INITIAL_POOL_SIZE; i++){
            connectionPool.add(createConnection());
        }
    }

    public static ConnectionPool getInstance() throws SQLException{
        if(instance == null){
            synchronized (ConnectionPool.class){
                if(instance == null){
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    private static Connection createConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


    public synchronized Connection gConnection(){
        if(connectionPool.isEmpty()){
            Ioutil.print("사용 가능한 커넥션 풀이 없습니다.... ");
            return null;
        }
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
        
    }

    public synchronized boolean releaseConnection(Connection connection) {
        connectionPool.add(connection); 
        return usedConnections.remove(connection); 
    }

    public synchronized void shutdown() throws SQLException {
        for (Connection connection : connectionPool) {
            connection.close();
            Ioutil.print("대기 중 connection 종료");
        }
        for (Connection connection : usedConnections) {
            connection.close();
            Ioutil.print("사용 중 connection 종료");
        }
    }
}
