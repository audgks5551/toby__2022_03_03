package springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 의존 관계를 설정하는 역할을 여기서 한다.
@Configuration
public class DaoFactory {
    // 코드 중복 제거
    @Bean
    public DConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }

    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }
}
