package ua.univ.lab8.Commands;

import ua.univ.lab8.Data.DAO.InvoiceDAO;
import ua.univ.lab8.Data.Models.User;
import ua.univ.lab8.Settings.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class AbortInvoiceCommand implements AgencyCommand {
    private int invoiceId;

    public AbortInvoiceCommand() {}


    @Override
    public void setData(Map<String, Object> mp) {
        invoiceId = Integer.valueOf((String) mp.get("invoiceId"));
    }

    @Override
    public boolean doAction(User who) {
        Settings.infoLogger.info(toString());

        try {
            Connection conn = DriverManager.getConnection(Settings.DB_URL, Settings.DB_USER, Settings.DB_PASSWORD);
            InvoiceDAO invoiceDAO = new InvoiceDAO(conn);

            invoiceDAO.payInvoice(invoiceId);

            invoiceDAO.closeConnection();
            return true;
        } catch (SQLException e) {
            Settings.errorLogger.error("Error", e);
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PayInvoiceCommand{");
        sb.append("invoiceId=").append(invoiceId);
        sb.append('}');
        return sb.toString();
    }
}
