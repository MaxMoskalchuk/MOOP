package ua.univ.lab8.Commands;

import ua.univ.lab8.Data.DAO.InvoiceDAO;
import ua.univ.lab8.Data.DAO.TourDAO;
import ua.univ.lab8.Data.DAO.UserDAO;
import ua.univ.lab8.Data.Models.Invoice;
import ua.univ.lab8.Data.Models.User;
import ua.univ.lab8.Settings.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class ChangeDiscountCommand implements AgencyCommand {
    private int userId;
    private double new_dis;
    @Override
    public void setData(Map<String, Object> mp)

    {
        userId = Integer.valueOf((String) mp.get("userId"));
        new_dis = Double.valueOf((String) mp.get("new_dis"));
    }

    public ChangeDiscountCommand() {}
    @Override
    public boolean doAction(User who) {
        Settings.infoLogger.info(toString());

        try {
            Connection conn = DriverManager.getConnection(Settings.DB_URL, Settings.DB_USER, Settings.DB_PASSWORD);
            UserDAO userDAO = new UserDAO(conn);
            userDAO.changeDiscount(userId,new_dis);
            return true;
        } catch (SQLException e) {
            Settings.errorLogger.error("Error", e);
        }
        return false;
    }
}
