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

public class BuyTourCommand implements AgencyCommand {
    private int tourId;
    @Override
    public void setData(Map<String, Object> mp)
    {
        tourId = Integer.valueOf((String) mp.get("tourId"));
    }

    public BuyTourCommand() {}
    @Override
    public boolean doAction(User who) {
        Settings.infoLogger.info(toString());

        try {
            Connection conn = DriverManager.getConnection(Settings.DB_URL, Settings.DB_USER, Settings.DB_PASSWORD);
            TourDAO tourDAO = new TourDAO(conn);
            UserDAO userDAO = new UserDAO(conn);
            Invoice  invoice = new Invoice();
            invoice.setTour(tourDAO.getTourById(tourId));
            invoice.setUser(who);

            InvoiceDAO invoiceDAO = new InvoiceDAO(conn);

            invoiceDAO.addInvoice(invoice);

            invoiceDAO.closeConnection();
            return true;
        } catch (SQLException e) {
            Settings.errorLogger.error("Error", e);
        }
        return false;
    }
}
