package ua.univ.lab8.Data.DAO;

import ua.univ.lab8.Settings.Settings;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AbstractDAO {
    protected Connection conn;

    public AbstractDAO(Connection conn) {
        this.conn = conn;
    }

    public void closeConnection() {
        try {
            if(conn != null) conn.close();
        } catch (SQLException e) {
            Settings.errorLogger.error("Error", e);
        }
    }
}