package ua.univ.lab8.Commands;

import ua.univ.lab8.Data.DAO.UserDAO;
import ua.univ.lab8.Data.Models.User;
import ua.univ.lab8.Settings.Settings;
import ua.univ.lab8.Utils.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class LoginCommand implements SignUpLoginCommand {
    private String userName;
    private String password;
    private User user;

    public LoginCommand() {}
    public LoginCommand(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setData(Map<String, Object> mp) {
        userName = (String) mp.get("userName");
        password = (String) mp.get("password");
    }

    @Override
    public boolean doAction(User who) {
        Settings.infoLogger.info("Login command" + this);

        if(who != null) return false;

        UserDAO userDAO = null;
        try {
            Connection conn = ConnectionPool.getConnection();
            userDAO = new UserDAO(conn);

            user = userDAO.authenticate(userName, password);

            userDAO.closeConnection();

            return user != null;
        } catch (SQLException e) {
            Settings.errorLogger.error("Error", e);
        } finally {
            if(userDAO != null)
                userDAO.closeConnection();
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LoginCommand{");
        sb.append("userName='").append(userName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}