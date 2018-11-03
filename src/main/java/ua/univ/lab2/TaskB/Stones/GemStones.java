package ua.univ.lab2.TaskB.Stones;

/**
 * Created by Asus on 03.11.2018.
 */
public abstract class GemStones {
    private double weight;
    private int price;
    private String name;
    private String manufacturer;
    private String transparency;

    public GemStones(String name, int price, double weight, String manufacturer, String transparency)
    {
        if(price < 0) throw new RuntimeException("Price couldn't be negative.");
        if(weight <= 0) throw new RuntimeException("Weight must be positive.");
        if(name == null || name.isEmpty()) throw new RuntimeException("Stone couldn't be anonymous.");
        if(transparency == null || transparency.isEmpty()) throw new RuntimeException("Stone couldn't be without transparency");

        this.name = name;
        this.price = price;
        this.weight = weight;
        this.manufacturer = manufacturer;
        this.transparency = transparency;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getTransparency() {
        return transparency;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Stone:\n\tName: %s\n\tPrice: %d\n\tWeight: %f\n\tManufacturer: %s\n\tTtransparency: %s",
                name,
                price,
                weight,
                manufacturer,
                transparency);
    }
    @Override
    public int hashCode() {
        long hash = (long)(9999999999L * weight);
        long pow = 153;
        final long mod = 5000000000053L, base = 153;
        String nameAndManufacturer = name + "_" + manufacturer;
        for(int i = 0 ; i < nameAndManufacturer.length() ; ++i) {
            hash = (hash * pow + (int)nameAndManufacturer.charAt(i)) % mod;
            if (hash < 0) hash += mod;
            pow = (pow * base) % mod;
            if (pow < 0) pow += mod;
        }
        return (int)(hash % Integer.MAX_VALUE);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof GemStones)) return false;
        GemStones other = (GemStones)obj;
        return name.equalsIgnoreCase(other.name) && Math.abs(weight - other.weight) < 1e-6;
    }
}
