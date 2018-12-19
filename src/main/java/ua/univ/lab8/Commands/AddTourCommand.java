package ua.univ.lab8.Commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

import ua.univ.lab8.Data.DAO.InvoiceDAO;
import ua.univ.lab8.Data.Models.User;
import ua.univ.lab8.Settings.Settings;
import ua.univ.lab8.Data.DAO.TourDAO;
import ua.univ.lab8.Data.Models.Tour;

import java.sql.Date;

public class AddTourCommand implements AgencyCommand{
    private String country;
    private double cost;
    private Date startTour;
    private Date endTour;
    boolean relx,excur, shop, hot;


    public AddTourCommand(){};

    public AddTourCommand(String country, double cost, Date startTour, Date endTour, boolean relx, boolean excur, boolean shop, boolean hot )
    {
        this.cost = cost;
        this.country = country;
        this.startTour = startTour;
        this.endTour = endTour;
        this.relx = relx;
        this.excur = excur;
        this.shop = shop;
        this.hot = hot;
    }


    @Override
    public void setData(Map<String, Object> mp)
    {
        country = String.valueOf((String) mp.get("country"));
        cost = Double.valueOf((String) mp.get("cost"));
        startTour = Date.valueOf((String) mp.get("startTour"));
        endTour = Date.valueOf((String) mp.get("endTour"));
        relx = Boolean.valueOf((String) mp.get("relx"));
        excur = Boolean.valueOf((String) mp.get("excur"));
        shop = Boolean.valueOf((String) mp.get("shop"));
        hot = Boolean.valueOf((String) mp.get("hot"));
    }


    @Override
    public boolean doAction(User who)
    {
        Settings.infoLogger.info(toString());

        try {
            Connection conn = DriverManager.getConnection(Settings.DB_URL, Settings.DB_USER, Settings.DB_PASSWORD);
            TourDAO tourDAO = new TourDAO(conn);
            int services = 1;
            if (relx) services |=1;
            if (excur) services |=2;
            if (shop) services |=4;
            if (hot) services |=8;
            tourDAO.addTour(who.getId(),this.country,this.cost,this.startTour, this.endTour, services);
            tourDAO.closeConnection();
            return true;
        } catch (SQLException e) {
            Settings.errorLogger.error("Error", e);
        }
        return false;

    }
}
