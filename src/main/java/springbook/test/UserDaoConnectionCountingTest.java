package springbook.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springbook.user.dao.CountingConnectionMaker;
import springbook.user.dao.CountingDaoFactory;
import springbook.user.dao.UserDao;

public class UserDaoConnectionCountingTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
        UserDao userDao = context.getBean("userDao", UserDao.class);

        /**
         * DAO 사용 코드
         */
        CountingConnectionMaker connectionMaker = context.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println("connectionMaker.getCounter() = " + connectionMaker.getCounter());
    }
}
