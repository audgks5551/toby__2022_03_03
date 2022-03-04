package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker{
    @Override
    public Connection makeNewConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost/springbook", "sbsst", "sbs123414"
        );
    }
}
