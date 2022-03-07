package springbook.learningtest.junit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springbook.user.dao.DaoFactory;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoFactory.class)
public class JUnitTest {
    @Autowired
    ApplicationContext applicationContext;
    static Set<JUnitTest> testObjects = new HashSet<>();
    static ApplicationContext contextObject = null;

    @Test
    public void test1() {
        assertThat(testObjects, not(hasItem(this)));
        testObjects.add(this);
    }

    @Test
    public void test2() {
        assertThat(testObjects, not(hasItem(this)));
        testObjects.add(this);
    }

    @Test
    public void test3() {
        assertThat(testObjects, not(hasItem(this)));
        testObjects.add(this);
    }
}
