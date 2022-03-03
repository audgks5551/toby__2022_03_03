package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.*;

// 슈퍼 클래스 : 변하는 부분을 메서드로 묶어 서브 클래스가 구현할 수 있도록 하는 클래스
// 즉 슈퍼 클래스는 미래에도 변경될 부분이 현저하게 줄어듬
public abstract class UserDao {

    /**
     * 1. 아래의 두 메서드의 공통부분을 하나로 묶음(중복제거)
     * 이것이 메서드 추출 기법!!
     * 리팩터링(refactoring): 결과의 변경 없이 코드의 구조를 재조정함
     *
     * 2. 그리고 상속을 통한 확장 -> abstract로 여러 종류의 DB접근을 가능하게 하는 클래스가 만들어짐
     * 이것이 템플릿 메서드 패턴(== 팩토리 메서드 패턴)!!
      */
    // protected void hookMethod() { } // 훅 메서드 : 선택적 오버라이드가능한 메서드
    public abstract Connection getConnection() throws SQLException; // 추상 메서드 : 반드시 구현해야하는 메서드


    public void add(User user) throws SQLException {
        Connection c = getConnection();

        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values(?,?,?)"
        );

        // ?에 들어감
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        int executeUpdate = ps.executeUpdate();// 업데이트 실행
        System.out.println(executeUpdate); // 1

        ps.close();
        c.close();
    }



    public User get(String id) throws SQLException {
        Connection c = getConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?"
        );

        // ?에 들어감
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        System.out.println(rs);

        rs.next(); // 커서 한 칸 옮김
        System.out.println(rs);

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }


}
