package ua.univ.lab2.TaskA;

/**
 * Created by Asus on 03.11.2018.
 */
public final class Star extends Astronomical_Body{
    Star(String name, int weight)
    {
        super(name, weight);
    }

    @Override
    public String toString() {
        return super.toString() + ", Type: Star";
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  Star)) return false;
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
