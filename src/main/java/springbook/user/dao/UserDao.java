package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.*;

public class UserDao {
    public void add(User user) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver"); // 생략 가능
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/springbook", "sbsst", "sbs123414"
        );

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

    public User get(String id) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.jdbc.Driver"); // 생략 가능
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/springbook", "sbsst", "sbs123414"
        );

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
