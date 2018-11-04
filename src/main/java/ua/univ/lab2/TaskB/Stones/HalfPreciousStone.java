package ua.univ.lab2.TaskB.Stones;

/**
 * Created by Asus on 04.11.2018.
 */
public class HalfPreciousStone extends GemStones {
    private String color;
    public HalfPreciousStone(String name, int price, double weight, String manufacturer, int transparency, String color)
    {

        super(name,price,weight,manufacturer,transparency);
        this.color=color;
    }

    public String getColor()
    {
        return  color;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("HalfPreciousStone:\n\t\t\t")
                .append(super.toString()+"\n\t\t\t")
                .append("Color: ")
                .append(color + "\n\t\t\t");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof HalfPreciousStone)) return false;
        HalfPreciousStone other = (HalfPreciousStone)obj;
        return super.equals(obj) && color.equalsIgnoreCase(other.color);
    }

    @Override
    public int hashCode() {
        long hash = 123456789010L * super.hashCode();
        long pow = 153;
        final long mod = 5000000000053L, base = 153;
        for(int i = 0 ; i < color.length() ; ++i) {
            hash = (hash * pow + (int)color.charAt(i)) % mod;
            if (hash < 0) hash += mod;
            pow = (pow * base) % mod;
            if (pow < 0) pow += mod;
        }
        return (int)(hash % Integer.MAX_VALUE);
    }
}


