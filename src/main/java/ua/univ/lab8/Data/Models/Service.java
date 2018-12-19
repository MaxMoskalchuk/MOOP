package ua.univ.lab8.Data.Models;

import ua.univ.lab8.Data.Enums.ServiceInfo;

public class Service {
    private int id;
    private String name;
    private ServiceInfo serviceInfo;

    public int getId() {
        return id;
    }

    public Service setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Service setName(String name) {
        this.name = name;
        return this;
    }

    public ServiceInfo getServiceInfo() {
        return serviceInfo;
    }

    public Service setServiceInfo(ServiceInfo serviceInfo) {
        this.serviceInfo = serviceInfo;
        return this;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", serviceInfo=" + serviceInfo +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Service)) return false;
        return Integer.valueOf(id).equals((((Service) obj)).getId());
    }
}