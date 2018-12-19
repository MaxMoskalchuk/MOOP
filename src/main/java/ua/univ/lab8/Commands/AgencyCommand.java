package ua.univ.lab8.Commands;

import ua.univ.lab8.Data.Models.User;

import java.util.Map;

public interface AgencyCommand {
    void setData(Map<String,Object> mp);
    boolean doAction(User who);
}