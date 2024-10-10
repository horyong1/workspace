package jdbcpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcPool {
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static final int INITIAL_POOL_SIZE = 5;

    // 데이터베이스 URL, 사용자명, 비밀번호 설정
    private static final String URL = "jdbc:mariadb://localhost:3306/test";
    private static final String USER = "scott";
    private static final String PASSWORD = "1234";

    // Connection Pool 생성
    public static JdbcPool create() throws SQLException {
        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection());
        }
        return new JdbcPool(pool);
    }

    private JdbcPool(List<Connection> connectionPool) {
        this.connectionPool = connectionPool;
    }

    // 새로운 연결 생성
    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // 연결 가져오기
    public Connection getConnection() {
        if (connectionPool.isEmpty()) {
            System.out.println("No available connections. Please wait...");
            return null; // 연결이 부족할 경우 처리 필요
        }
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    // 연결 반환
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    // 현재 사용 중인 연결 수
    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }
    
    // Connection Pool 종료
    public void shutdown() throws SQLException {
        for (Connection connection : connectionPool) {
            connection.close();
        }
        for (Connection connection : usedConnections) {
            connection.close();
        }
    }
}
