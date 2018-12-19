package ua.univ.lab8.Data.DAO;

import ua.univ.lab8.Data.Enums.UserRoles;
//import ua.univ.lab8.Data.Models.Call;
//import ua.univ.lab8.Data.Models.Invoice;
import ua.univ.lab8.Data.Models.Service;
import ua.univ.lab8.Data.Models.User;
import ua.univ.lab8.Settings.Settings;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UserDAO extends AbstractDAO {
    private Map<Integer , User> mp = new TreeMap<Integer , User>();

    public UserDAO(Connection conn) {
        super(conn);
    }

    public User addUser(String userName, String password) {
        try {
            String query = "INSERT INTO `Users`(`USER_NAME`, `USER_PASSWORD`, `USER_DISCOUNT`, `USER_TOURS`) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, userName);
            ps.setString(2, password);
            ps.setDouble(3,0.0);
            ps.setInt(4,0);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            Integer newId = null;
            if(rs.next())
                newId = rs.getInt(1);

            ps.close();

            if(newId != null)
                return getUserById(newId);
        } catch (SQLException e) {
            Settings.errorLogger.error("Error", e);
        }
        return null;
    }


    public User authenticate(String userName, String password) {
        try {
            final String query = String.format("SELECT * FROM `Users` WHERE " +
                    "`USER_NAME` = '%s' AND `USER_PASSWORD` = '%s'", userName, password);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            User user = null;
            if(resultSet.next()) {
                user = new User()
                        .setId(resultSet.getInt("USER_ID"))
                        .setUserName(resultSet.getString("USER_NAME"))
                        .setUserPassword(resultSet.getString("USER_PASSWORD"))
                        .setDiscount(resultSet.getDouble("USER_DISCOUNT"))
                        .setTours(resultSet.getInt("USER_TOURS"));

                String role = resultSet.getString("USER_ROLE");
                user.setRole(role.equals("SUBSCRIBER") ? UserRoles.SUBSCRIBER : UserRoles.ADMINISTRATOR);

             }

            if(user != null) {
                mp.put(user.getId() , user);
            }

            resultSet.close();
            statement.close();

            return user;
        } catch (SQLException e) {
            Settings.errorLogger.error("Error", e);
        }

        return null;
    }

    public User changeDiscount(int id, double dis)
    {
        try {
            final String query = String.format("UPDATE `Users` SET `USER_DISCOUNT`="+Double.toString(dis)+" WHERE `USER_ID` = '%d'",id);
            Statement statement = conn.createStatement();
            statement.execute(query);

            User user = getUserById(id);

            if(user != null) {
                mp.put(id , user);
            }

            statement.close();

            return user;
        } catch (SQLException e) {
            Settings.errorLogger.error("Error", e);
        }

        return null;
    }

    public User addTour(int id, int tours)
    {
        try {
            final String query = String.format("UPDATE `Users` SET `USER_TOURS`="+Integer.toString(tours)+" WHERE `USER_ID` = '%d'",id);
            Statement statement = conn.createStatement();
            statement.execute(query);

            User user = getUserById(id);

            if(user != null) {
                mp.put(id , user);
            }

            statement.close();

            return user;
        } catch (SQLException e) {
            Settings.errorLogger.error("Error", e);
        }

        return null;
    }

    public User getUserById(int id) {
        if(mp.containsKey(id)) {
            return mp.get(id);
        }

        try {
            final String query = String.format("SELECT * FROM `Users` WHERE `USER_ID` = '%d'", id);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            User user = null;
            if(resultSet.next()) {
                user = new User()
                        .setId(id)
                        .setUserName(resultSet.getString("USER_NAME"))
                        .setUserPassword(resultSet.getString("USER_PASSWORD"))
                        .setDiscount(resultSet.getDouble("USER_DISCOUNT"))
                        .setTours(resultSet.getInt("USER_TOURS"));


                String role = resultSet.getString("USER_ROLE");
                user.setRole(role.equals("SUBSCRIBER") ? UserRoles.SUBSCRIBER : UserRoles.ADMINISTRATOR);
            }

            if(user != null) {
                mp.put(id , user);
            }

            resultSet.close();
            statement.close();

            return user;
        } catch (SQLException e) {
            Settings.errorLogger.error("Error", e);
        }

        return null;
    }

    public List<User> getAllUsers() {
        try {
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("SELECT * FROM `Users` WHERE 1");
            for(User user : mp.values()) {
                queryBuilder.append(String.format(" AND `USER_ID` <> %d", user.getId()));
            }

            final String query = queryBuilder.toString();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            List<User> result = new ArrayList<User>();
            result.addAll(mp.values());

            while(resultSet.next()) {
                User user = new User()
                        .setId(resultSet.getInt("USER_ID"))
                        .setUserName(resultSet.getString("USER_NAME"))
                        .setUserPassword(resultSet.getString("USER_PASSWORD"))
                        .setDiscount(resultSet.getDouble("USER_DISCOUNT"))
                        .setTours(resultSet.getInt("USER_TOURS"));

                String role = resultSet.getString("USER_ROLE");
                user.setRole(role.equals("SUBSCRIBER") ? UserRoles.SUBSCRIBER : UserRoles.ADMINISTRATOR);

                mp.put(user.getId(), user);
                result.add(user);
            }

            resultSet.close();
            statement.close();

            return result;
        } catch (SQLException e) {
            Settings.errorLogger.error("Error", e);
        }

        return null;
    }

}