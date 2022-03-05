package springbook.test;

import springbook.user.dao.ConnectionMaker;
import springbook.user.dao.DConnectionMaker;
import springbook.user.dao.DaoFactory;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException {
        UserDao userDao = new DaoFactory().userDao();

        User user = new User();
        user.setId("hello3");
        user.setName("이명한");
        user.setPassword("password");

        userDao.add(user);

        User findUser = userDao.get(user.getId());
        System.out.println("findUser.getId() = " + findUser.getId());
        System.out.println("findUser = " + findUser.getName());
        System.out.println("findUser = " + findUser.getPassword());
    }
}
