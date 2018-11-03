package ua.univ.lab2.TaskA;

import java.util.ArrayList;

/**
 * Created by Asus on 03.11.2018.
 */
public final class Planet extends Astronomical_Body{

    private ArrayList<Moon> Moons = new ArrayList<Moon>();
    Planet(String name, int weight, ArrayList<Moon> moons)
    {
        super(name,weight);
        Moons = moons;
    }

    @Override
    public String toString() {
        String str = "No moons";
        if(!Moons.isEmpty()) str = Moons.toString();
        return super.toString() + ", Type: Planet, Moons: " + str + ";";
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Planet)) return false;
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
