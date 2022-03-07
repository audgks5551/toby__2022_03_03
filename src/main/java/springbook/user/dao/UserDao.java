package springbook.user.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.*;

// 슈퍼 클래스 : 변하는 부분을 메서드로 묶어 서브 클래스가 구현할 수 있도록 하는 클래스
// 즉 슈퍼 클래스는 미래에도 변경될 부분이 현저하게 줄어듬
public class UserDao {
    /**
     * 1. 아래의 두 메서드의 공통부분을 하나로 묶음(중복제거)
     * 이것이 메서드 추출 기법!!
     * 리팩터링(refactoring): 결과의 변경 없이 코드의 구조를 재조정함
     *
     * 2. 그리고 상속을 통한 확장 -> abstract로 여러 종류의 DB접근을 가능하게 하는 클래스가 만들어짐
     * 이것이 템플릿 메서드 패턴(== 팩토리 메서드 패턴)!!
     *
     * 3. DB Connection을 인터페이스로 주입
     * 이것이 의존성 주입!!
     * 주입을 설정하는 곳은 밖에서 한다.
     * 결론적으로 매번 여기로 들어와 DB Connection을 수작업으로 주입할 필요가 없어졌다
     *
     * ** 개방 폐쇄 원칙 (OCP: The Open Closed Principle) **
     * - DB 연결방법은 확장
     * - 핵심기능은 폐쇄
     *
     * ** 전략 패턴 **
     * 자신의 기능 맥락에서, 필요에 따라 변경이 필요한 알고리즘을 인터페이스를 통해 통째로 외부로 분리시키고,
     * 이를 구현한 구체적인 알고리즘 클래스를 필요에 따라 바꿔서 사용할 수 있게 하는 디자인패턴이다
     * (1) UserDao == context
     * (2) UserDaoTest == client : context의 생성자를 주입
      */
    // protected void hookMethod() { } // 훅 메서드 : 선택적 오버라이드가능한 메서드
//    public abstract Connection getConnection() throws SQLException; // 추상 메서드 : 반드시 구현해야하는 메서드
    private ConnectionMaker connectionMaker; // 기존의 커넥션을 독립적으로 만듬
    private DataSource dataSource;
    public UserDao() {}

    // 변경 부분을 인터페이스로 만들어 주게 되면 이 클래스의 변경없이 다양한 Connection을 연결할 수 있다.
    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    // 수정자 메서드
    public void setConnectionMaker(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws SQLException {
        Connection c = dataSource.getConnection();
        System.out.println("DB 연동됨");

        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values(?,?,?)"
        );

        if (!(ps == null)) {
            System.out.println("고정 값 입력 완료");
        }

        // ?에 들어감
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());
        System.out.println("변수 값 입력 완료");

        int executeUpdate = ps.executeUpdate();// 업데이트 실행

        if (executeUpdate == 1) {
            System.out.println("저장 완료"); // 1
        } else {
            System.out.println("저장 안됨");
        }

        ps.close();
        c.close();
    }

    public User get(String id) throws SQLException {
        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?"
        );

        // ?에 들어감
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();

        // 커서 한 칸 옮김
        User user = null;
        if(rs.next()) {
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
        }

        rs.close();
        ps.close();
        c.close();

        if (user == null) {
            throw new EmptyResultDataAccessException(1);
        }

        return user;
    }

    public void deleteAll() throws SQLException {
        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "delete from users"
        );

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public int getCount() throws SQLException {
        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement(
                "select count(*) from users"
        );

        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        ps.close();
        c.close();

        return count;
    }
}
