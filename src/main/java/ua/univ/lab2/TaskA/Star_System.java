package ua.univ.lab2.TaskA;

import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;

/**
 * Created by Asus on 03.11.2018.
 */
public class Star_System {
    private ArrayList<Star> Stars = new ArrayList<Star>();
    private ArrayList<Planet> Planets = new ArrayList<Planet>();
    private String Name;
    Star_System(String name,ArrayList<Star> stars, ArrayList<Planet> planets)
    {
        if (stars.isEmpty())
        {

        } else
        {
            Name = name;
            Stars = stars;
            Planets = planets;
        }
    }

    public void AddPlanet(Planet planet)
    {
        this.Planets.add(planet);
    }

    public void NumberOfPlanets()
    {
        System.out.println("Number of planets in the " + this.Name + " - " + Planets.size());
    }

    public void NameOfStars()
    {
        System.out.println(Stars.toString());
    }

    @Override
    public String toString() {
        return "Name: " + this.Name + ", Stars: " + this.Stars.toString() + ", Planets: " + this.Planets.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Star_System)) return false;
        Star_System other = (Star_System) obj;
        String currentName = Name;
        String otherName = other.Name;
        return currentName == otherName;
    }

}
