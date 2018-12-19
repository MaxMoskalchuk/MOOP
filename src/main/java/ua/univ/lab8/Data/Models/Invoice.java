package ua.univ.lab8.Data.Models;

public class Invoice {
    private int id;
    private User user;
    private Tour tour;
    private double amount;

    public int getId() {
        return id;
    }
    public Invoice setId (int id)
    {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Invoice setUser(User user) {
        this.user = user;
        return this;
    }

    public Tour getTour() {
        return tour;
    }

    public Invoice setTour(Tour tour) {
        this.tour = tour;
        return this;
    }

    public double getAmount() {
        amount = (1-user.getDiscount())*tour.getCost();
        return amount;

    }
    @Override
    public String toString()
    {
        return "Invoice{" +
                "id=" + id +
                ", user=" + user +
                ", tour=" + tour +
                ", amount=" + amount +
                "}";
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Invoice)) return false;
        return Integer.valueOf(id).equals((((Invoice) obj)).getId());
    }
}
