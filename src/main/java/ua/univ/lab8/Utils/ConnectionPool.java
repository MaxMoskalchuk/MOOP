package ua.univ.lab8.Utils;

import org.apache.commons.dbcp.BasicDataSource;

import ua.univ.lab8.Settings.Settings;

import java.sql.Connection;
import java.sql.SQLException;

public final class ConnectionPool {
    private static BasicDataSource dataSource = null;

    private ConnectionPool() {}

    private static void lazyInit() {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(Settings.DB_DRIVER);
        dataSource.setUrl(Settings.DB_URL);
        dataSource.setUsername(Settings.DB_USER);
        dataSource.setPassword(Settings.DB_PASSWORD);
    }

    public static Connection getConnection()
            throws SQLException
    {
        if(dataSource == null)
            lazyInit();
        return dataSource.getConnection();
    }


}