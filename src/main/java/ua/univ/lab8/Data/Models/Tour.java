package ua.univ.lab8.Data.Models;


import ua.univ.lab8.Data.Enums.ServiceInfo;

import java.sql.Timestamp;
import java.sql.Date;

public class Tour {
    private int id;
    private User agency;
    private String country;
    private double cost;
    private Date startTour;
    private Date endTour;
    private int tourService = 0;

    private String services;

    public int getId() {
        return id;
    }

    public Tour setId(int id)
    {
        this.id = id;
        return this;
    }

    public User getAgency() {
        return agency;
    }

    public Tour setAgency(User agency)
    {
        this.agency = agency;
        return this;
    }

    public String getCountry()
    {
        return this.country;
    }

    public Tour setCountry(String country) {
        this.country = country;
        return this;
    }

    public double getCost() {
        return cost;
    }

    public Tour setCost(double cost) {
        this.cost = cost;
        return this;
    }

    public Date getStartTour() {
        return startTour;
    }

    public Tour setStartTour(Date startTour) {
        this.startTour = startTour;
        return this;
    }

    public Date getEndTour() {
        return endTour;
    }

    public Tour setEndTour(Date endTour) {
        this.endTour = endTour;
        return this;
    }

    public int getTourService() {
        return tourService;
    }

    public String getServices()
    {
        String servicess="";
        if ((tourService&1) == 1) servicess= servicess + " RELAXATION";
        if ((tourService&2) == 2) servicess= servicess + " EXCURSION";
        if ((tourService&4) == 4) servicess= servicess + " SHOPPING";
        if ((tourService&8) == 8) servicess= servicess + " HOT";
        return  servicess;
    }

    public Tour addTourService(int serviceInfo)
    {
        tourService |= serviceInfo;
        services= getServices();
        return this;
    }
    public Tour addTourService(ServiceInfo serviceInfo)
    {
        return addTourService(serviceInfo.value());
    }

    @Override
    public String toString()
    {
        return "Tour{"+
                "id="+id +
                ", Agency=" + agency +
                ", country=" + country +
                ", startTour=" + startTour +
                ", endTour=" + endTour +
                "}";
    }
}
