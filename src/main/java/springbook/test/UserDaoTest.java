package springbook.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springbook.user.dao.DaoFactory;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserDaoTest {
    @Test
    public void addAndGet() throws SQLException {

        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao userDao = context.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("hyumee1");
        user.setName("김유림");
        user.setPassword("springno1");

        userDao.add(user);

        User findUser = userDao.get(user.getId());

        assertThat(findUser.getName(), is(user.getName()));
        assertThat(findUser.getPassword(), is(user.getPassword()));
    }
}
