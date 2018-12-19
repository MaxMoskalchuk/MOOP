package ua.univ.lab8.Commands;

import ua.univ.lab8.Data.DAO.UserDAO;
import ua.univ.lab8.Data.Models.User;
import ua.univ.lab8.Settings.Settings;
import ua.univ.lab8.Utils.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class SignUpCommand implements SignUpLoginCommand {
    private String userName;
    private String password;
    private String confirmPassword;
    private User   user;

    public SignUpCommand() {
        super();
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setData(Map<String, Object> mp) {
        userName = ((String) mp.get("userName")).trim();
        password = ((String) mp.get("password")).trim();
        confirmPassword = ((String) mp.get("confirmPassword")).trim();
    }

    @Override
    public boolean doAction(User who) {
        Settings.infoLogger.info(toString());

        try {
            Connection conn = ConnectionPool.getConnection();

            if(!password.equals(confirmPassword))
                throw new RuntimeException("You made a typo in your passwords.");

            if(userName.isEmpty())
                throw new RuntimeException("Username could not be empty.");

            if(password.isEmpty())
                throw new RuntimeException("Password could not be empty.");

            if(!userName.matches("[^\\s-]+"))
                throw new RuntimeException("Whitespaces are not allowed in the username.");

            if(!password.matches("[^\\s-]+"))
                throw new RuntimeException("Whitespaces are not allowed int the password.");

            UserDAO userDAO = new UserDAO(conn);

            this.user = userDAO.addUser(userName, password);

            userDAO.closeConnection();
            return true;
        } catch (SQLException e) {
            Settings.errorLogger.error("Error", e);
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SignUpCommand{");
        sb.append("userName='").append(userName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", confirmPassword='").append(confirmPassword).append('\'');
        sb.append('}');
        return sb.toString();
    }
}