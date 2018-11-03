package ua.univ.lab2.TaskA;

/**
 * Created by Asus on 03.11.2018.
 */
public final class Moon extends Astronomical_Body    {

    Moon(String name,int weight)
    {
        super(name,weight);
    }

    @Override
    public String toString() {
        return super.toString() + " Type: Moon";
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Moon)) return false;
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
