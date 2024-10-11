package jdbcpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ioutil.Ioutil;

public class JdbcPool {
    // 사용할 수 있는 커넥션을 저장하는 리스트(연결 풀)
    private List<Connection> connectionPool;
    // 사용 중인 커넥션을 저장하는 리스트
    private List<Connection> usedConnections = new ArrayList<>();
    // 초기 연결 풀의 크기를 설정(5개로 연결로 시작)
    private static final int INITIAL_POOL_SIZE = 5;

    // 데이터베이스 URL, 사용자명, 비밀번호 설정
    // JDBC URL은 MariaDB 데이터베이스를 가리킴
    private static final String URL = "jdbc:mariadb://localhost:3306/test";
    private static final String USER = "scott";
    private static final String PASSWORD = "1234";

    // 싱글톤 인스턴스를 위한 static 변수
    private static JdbcPool instance;


    // 정적 팩토리 메서드 : 커넥션 풀을 생성하고 반환
    // 예외 처리로 SQLException을 던짐 (DB 연결 시 발생 가능)
    // Connection Pool 생성
    public JdbcPool() throws SQLException {
        // 커넥션 풀을 저장할 리스트 생성
        connectionPool  = new ArrayList<>(INITIAL_POOL_SIZE);

        //설정된 초기 크기(INITIAL_POOL_SIZE)만큼 커넥션을 생성하여 풀에 추가 
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            connectionPool.add(createConnection());   // 새로운 DB 커넥션을 생성하여 풀에 넣음
        }
    }

    // 싱글톤 인스턴스를 얻는 메서드
    public static JdbcPool getInstance() throws SQLException {
        if (instance == null) {
            synchronized (JdbcPool.class) {
                if (instance == null) {
                    instance = new JdbcPool();
                }
            }
        }
        return instance;
    }

    // // JdbcPool 생성자 : 외부에서 호출하지 못하도록 private 설정
    // // connectionPool 리스트를 초기화함
    // private JdbcPool(List<Connection> connectionPool) {
    //     this.connectionPool = connectionPool;
    // }


    // 새로운 연결 생성
    // DriverManager를 이요하여 데이터베이스 연결을 생성하고 반환
    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


    // 커넥션을 풀에서 가져오는 메서드 
    // 사용 가능한 커넥션이 없으면 메세지를 출력하고 null을 반환
    public synchronized Connection getConnection() {
        if (connectionPool.isEmpty()) {
            System.out.println("사용 가능한 연결 커넥션 풀이 없습니다. Please wait...");
            return null; // 연결이 부족할 경우 처리 필요
        }
        // 풀에서 마지막 커넥션을 꺼내서 사용중인 리스트에 추가
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;  // 사용 가능한 커넥션 반환
    }

    // 사용한 커넥션을 반환하는 메서드
    // 커넥션 풀에 다시 추가하고 사용 중 리스트에서 제거
    public synchronized boolean releaseConnection(Connection connection) {
        connectionPool.add(connection); // 커넥션 풀에 다시 추가
        return usedConnections.remove(connection);  // 사용 중 리스트에서 제거
    }

    // 현재 사용 중인 연결 수
    public synchronized int getSize() {
        return connectionPool.size() + usedConnections.size();
    }
    
    // Connection Pool 종료
    // 사용 중이거나 풀에 있는 모든 커넥션을 닫음
    // SQLException이 발생할 수 있으므로 예외처리 필요
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
