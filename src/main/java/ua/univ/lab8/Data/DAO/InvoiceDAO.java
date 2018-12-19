package ua.univ.lab8.Data.DAO;

import ua.univ.lab8.Data.Models.Invoice;
import ua.univ.lab8.Settings.Settings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class InvoiceDAO extends AbstractDAO {
    private static Map<Integer, Invoice> mp = new TreeMap<>();

    public InvoiceDAO(Connection conn) {
        super(conn);
    }

    public void addInvoice(Invoice invoiceModel) {
        try {
            String query = "INSERT INTO `Invoices`(`USER_ID`, `TOUR_ID`, `AMOUNT`) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, invoiceModel.getUser().getId());
            ps.setInt(2, invoiceModel.getTour().getId());
            ps.setDouble(3, invoiceModel.getAmount());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            Settings.errorLogger.error("Error", e);
        }
    }

    public double getDebtForUser(int userId) {
        List<Invoice> invoices = getInvoicesForUser(userId);
        double debt = 0;
        for(Invoice invoice : invoices) {
            debt += invoice.getAmount();
        }
        return debt;
    }

    public void payInvoice(int invoiceId) {
        try {
            String query = "DELETE FROM `Invoices` WHERE `INV_ID` = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, invoiceId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            Settings.errorLogger.error("Error", e);
        }
    }

    public List<Invoice> getInvoicesForUser(int userId) {
        try {
            String query = "SELECT * FROM `Invoices` WHERE `USER_ID` = " + userId;
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            List<Invoice> invoices = new ArrayList<>();
            while(resultSet.next()) {
                Invoice invoice = new Invoice()
                        .setId(resultSet.getInt("INV_ID"));

                UserDAO userDAO = new UserDAO(conn);
                TourDAO callDAO = new TourDAO(conn);

                invoice.setUser(userDAO.getUserById(resultSet.getInt("USER_ID")));
                invoice.setTour(callDAO.getTourById(resultSet.getInt("TOUR_ID")));

                invoices.add(invoice);
            }

            resultSet.close();
            statement.close();

            return invoices;
        } catch (SQLException e) {
            Settings.errorLogger.error("Error", e);
        }
        return null;
    }
}