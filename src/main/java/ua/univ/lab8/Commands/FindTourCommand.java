package ua.univ.lab8.Commands;

import ua.univ.lab8.Data.DAO.TourDAO;
import ua.univ.lab8.Data.DAO.UserDAO;
import ua.univ.lab8.Data.Models.User;
import ua.univ.lab8.Settings.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class FindTourCommand implements AgencyCommand {
    private String country;

    @Override
    public void setData(Map<String, Object> mp)

    {
        country = String.valueOf((String) mp.get("country"));
    }

    public FindTourCommand() {}
    @Override
    public boolean doAction(User who) {
        Settings.infoLogger.info(toString());

        try {
            Connection conn = DriverManager.getConnection(Settings.DB_URL, Settings.DB_USER, Settings.DB_PASSWORD);
            TourDAO tourDAO = new TourDAO(conn);
            who.setGlobalCountry(country);
            return true;
        } catch (SQLException e) {
            Settings.errorLogger.error("Error", e);
        }
        return false;
    }
}
