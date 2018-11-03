package ua.univ.lab2.TaskB.Stones;

/**
 * Created by Asus on 03.11.2018.
 */
public class PreciousStone extends GemStones {
    private int hardness;
    private String color;
    public PreciousStone(String name, int price, double weight, String manufacturer, String transparency, int hardness, String color)
    {

        super(name,price,weight,manufacturer,transparency);
        if(hardness<0 || hardness>10) throw new RuntimeException("Hardness must be in range 1-10");
        this.hardness=hardness;
        this.color=color;
    }

    public int getHardness()
    {
        return hardness;
    }
    public String getColor()
    {
        return  color;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PreiousStone:\n\t")
                .append(super.toString())
                .append("Color: ")
                .append(color + "\n\t")
                .append("Hardness: ")
                .append(hardness + "\n\t");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof PreciousStone)) return false;
        PreciousStone other = (PreciousStone)obj;
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
