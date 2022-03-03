package springbook.test.db;


import springbook.user.dao.UserDao;
import springbook.user.domain.User;

import java.sql.SQLException;

public class ConnectionTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();

        // 등록
        User user = new User();
        user.setId("whiteship4");
        user.setName("백기선");
        user.setPassword("married");
        userDao.add(user);
        System.out.println(user.getId() + " 등록 성공");

        // 조회
        User findUser = userDao.get(user.getId());
        System.out.println(findUser.getName());
        System.out.println(findUser.getPassword());
        System.out.println(findUser.getId() + " 조회 성공");
    }
}
