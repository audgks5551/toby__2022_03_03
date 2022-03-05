package springbook.user.dao;

// 의존 관계를 설정하는 역할을 여기서 한다.
public class DaoFactory {
    // 코드 중복 제거
    private DConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }

    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }
}
