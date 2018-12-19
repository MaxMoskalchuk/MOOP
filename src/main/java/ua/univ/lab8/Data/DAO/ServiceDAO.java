package ua.univ.lab8.Data.DAO;

import ua.univ.lab8.Data.Enums.ServiceInfo;
import ua.univ.lab8.Data.Models.Service;
import ua.univ.lab8.Data.Models.User;
import ua.univ.lab8.Settings.Settings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ServiceDAO extends AbstractDAO {
    private Map<Integer, Service> mp = new TreeMap<Integer, Service>();

    public ServiceDAO(Connection conn) {
        super(conn);
    }


    public Service getServiceByID(int id) {
        if(mp.containsKey(id)) {
            return mp.get(id);
        }

        try {
            final String query = String.format("SELECT * FROM `Services` WHERE `SRV_ID` = %d", id);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            Service service = null;
            if(resultSet.next()) {
                service = new Service()
                        .setId(id)
                        .setName(resultSet.getString("SRV_NAME"));

                String serviceInfo = resultSet.getString("SRV_INFO");
                service.setServiceInfo(ServiceInfo.valueOf(serviceInfo));
            }

            if(service != null) {
                mp.put(id, service);
            }

            resultSet.close();
            statement.close();

            return service;
        } catch (SQLException e) {
            Settings.errorLogger.error("Error", e);
        }

        return null;
    }

    private List<Service> getServicesListImpl(String param) {
        try {
            final String query = ("SELECT * FROM `UsersAndServices` " + param).trim();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            List<Integer> ids = new ArrayList<Integer>();
            while(resultSet.next()) {
                ids.add(resultSet.getInt("SRV_ID"));
            }

            resultSet.close();
            statement.close();

            List<Service> result = new ArrayList<Service>();
            for(Integer id : ids) {
                Service srv = getServiceByID(id);
                if(srv != null) {
                    result.add(srv);
                }
            }

            return result;
        } catch (SQLException e) {
            Settings.errorLogger.error("Error", e);
        }

        return null;
    }

    public List<Service> getServicesForUser(User user) {
        return getServicesListImpl(String.format("WHERE `USER_ID` = %d", user.getId()));
    }

    public List<Service> getAllServices() {
        try {
            final String query = ("SELECT * FROM `Services` WHERE 1");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            List<Service> services = new ArrayList<>();
            while(resultSet.next()) {
                Service service = new Service()
                        .setId(resultSet.getInt("SRV_ID"))
                        .setName(resultSet.getString("SRV_NAME"));

                String serviceInfo = resultSet.getString("SRV_INFO");
                service.setServiceInfo(ServiceInfo.valueOf(serviceInfo));

                mp.put(service.getId(), service);
                services.add(service);
            }

            resultSet.close();
            statement.close();

            return services;
        } catch (SQLException e) {
            Settings.errorLogger.error("Error", e);
        }

        return null;
    }
}