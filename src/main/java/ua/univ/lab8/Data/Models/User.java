package ua.univ.lab8.Data.Models;

import ua.univ.lab8.Data.Enums.ServiceInfo;
import ua.univ.lab8.Data.Enums.UserRoles;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String userName;
    private String userPassword;
    private UserRoles role;
    private double discount;
    private int tours;
    private String GlobalCountry = "";

    public void setGlobalCountry(String country)
    {
        this.GlobalCountry=country;
    }
    public String getGlobalCountry()
    {
        return this.GlobalCountry;
    }
    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public User setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }

    public UserRoles getRole() {
        return role;
    }

    public User setRole(UserRoles role) {
        this.role = role;
        return this;
    }

    public double getDiscount() {
        return discount;
    }

    public User setDiscount(double discont) {
        this.discount = discont;
        return this;
    }

    public int getTours() {
        return tours;
    }

    public User setTours(int tours) {
        this.tours = tours;
        return  this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", userPassword='").append(userPassword).append('\'');
        sb.append(", role=").append(role);
        sb.append(", discount").append(discount);
        sb.append(", tours").append(tours);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof User)) return false;
        return Integer.valueOf(id).equals((((User) obj)).getId());
    }
}