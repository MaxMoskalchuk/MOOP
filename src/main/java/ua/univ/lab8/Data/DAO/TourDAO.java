package ua.univ.lab8.Data.DAO;

import ua.univ.lab8.Data.Models.Tour;
import ua.univ.lab8.Data.Models.User;
import ua.univ.lab8.Settings.Settings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.Map;

public class TourDAO extends AbstractDAO
{
    private Map<Integer , Tour> mp = new TreeMap<Integer, Tour>();


    public TourDAO(Connection conn) {
        super(conn);
    }

    public Tour addTour(int agencyID, String country, double cost, Date start, Date end, int services) {
        try {
            String query = "INSERT INTO `Tours`(`TOUR_AGENCY`, `TOUR_COUNTRY`, `TOUR_COST`, `TOUR_START`, `TOUR_END`, `TOUR_SERVICES`) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, agencyID);
            ps.setString(2, country);
            ps.setDouble(3,cost);
            ps.setDate(4,start);
            ps.setDate(5,end);
            ps.setInt(6,services);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            Integer newId = null;
            if(rs.next())
                newId = rs.getInt(1);

            ps.close();

            if(newId != null)
                return getTourById(newId);
        } catch (SQLException e) {
            Settings.errorLogger.error("Error", e);
        }
        return null;
    }

    public Tour getTourById(int id)
    {
        if(mp.containsKey(id)) {
            return mp.get(id);
        }
        try {
            final String query = String.format("SELECT * FROM `Tours` WHERE `TOUR_ID` = '%d'", id);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            Tour tour = null;
            if(resultSet.next()) {
                tour = new Tour()
                        .setId(id)
                        .setCountry(resultSet.getString("TOUR_COUNTRY"))
                        .setCost(resultSet.getDouble("TOUR_COST"))
                        .setStartTour(resultSet.getDate("TOUR_START"))
                        .setEndTour(resultSet.getDate("TOUR_END"))
                        .addTourService(resultSet.getInt("TOUR_SERVICES"));

                UserDAO userDAO = new UserDAO(conn);
                tour.setAgency(userDAO.getUserById(resultSet.getInt("TOUR_AGENCY")));
            }

            if(tour != null) {
                mp.put(id , tour);
            }

            resultSet.close();
            statement.close();

            return tour;
        } catch (SQLException e) {
            Settings.errorLogger.error("Error", e);
        }

        return null;

    }
    public List<Tour> getAllTour()
    {
        try
        {
            final String query = String.format("SELECT * FROM `Tours` " +
                    "WHERE  `TOUR_START` > " +  new Date(System.currentTimeMillis()));

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            List<Tour> result = new ArrayList<Tour>();
            while(resultSet.next())
            {
                Tour tour = new Tour()
                        .setId(resultSet.getInt("TOUR_ID"))
                        .setCountry(resultSet.getString("TOUR_COUNTRY"))
                        .setCost(resultSet.getDouble("TOUR_COST"))
                        .setStartTour(resultSet.getDate("TOUR_START"))
                        .setEndTour(resultSet.getDate("TOUR_END"))
                        .addTourService(resultSet.getInt("TOUR_SERVICES"));


                UserDAO userDAO = new UserDAO(conn);
                tour.setAgency(userDAO.getUserById(resultSet.getInt("TOUR_AGENCY")));

                result.add(tour);
                mp.put(tour.getId(), tour);

            }
            resultSet.close();
            statement.close();

            return result;

        }
        catch (SQLException e) {
            Settings.errorLogger.error("Error", e);
        }
        return null;

    }

    public List<Tour> getTourByCountry(String country)
    {
        if (country=="") return getAllTour();
        try
        {
            final String query = String.format("SELECT * FROM `Tours` " +
                                                "WHERE  `TOUR_COUNTRY` = \"" +country+ "\"");

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            List<Tour> result = new ArrayList<Tour>();
            while(resultSet.next())
            {
                Tour tour = new Tour()
                        .setId(resultSet.getInt("TOUR_ID"))
                        .setCountry(resultSet.getString("TOUR_COUNTRY"))
                        .setCost(resultSet.getDouble("TOUR_COST"))
                        .setStartTour(resultSet.getDate("TOUR_START"))
                        .setEndTour(resultSet.getDate("TOUR_END"))
                        .addTourService(resultSet.getInt("TOUR_SERVICES"));


                UserDAO userDAO = new UserDAO(conn);
                tour.setAgency(userDAO.getUserById(resultSet.getInt("TOUR_AGENCY")));

                result.add(tour);
                mp.put(tour.getId(), tour);

            }
            resultSet.close();
            statement.close();

            return result;

        }
        catch (SQLException e) {
            Settings.errorLogger.error("Error", e);
        }
        return null;
    }
}
