package ua.univ.lab2.TaskA;

import java.util.ArrayList;

/**
 Создать  объект  класса  Звездная  система,  используя  классы  Планета,
 Звезда, Луна. Методы: вывести на консоль количество планет в звездной  системе,
 название звезды, добавление планеты в систему.
 */
public class taskA {
    public static void main(String[] args)
    {
        Star star1 = new Star("Sun", 120);
        Moon moon = new Moon("Moon", 20);
        ArrayList<Moon> moons = new ArrayList<>();
        moons.add(moon);
        Planet planet1 = new Planet("Earth", 50, moons);
        Planet planet2 = new Planet("Venus", 28, new ArrayList<Moon>());
        Planet planet3 = new Planet( "Mars", 40, new ArrayList<Moon>());

        ArrayList<Star> stars = new ArrayList<>();
        stars.add(star1);
        ArrayList<Planet> planets = new ArrayList<>();
        planets.add(planet1);
        planets.add(planet2);

        Star_System system = new Star_System("Solar System",stars,planets);
        System.out.println(system.toString());
        system.AddPlanet(planet3);
        System.out.println(system.toString());
        system.NumberOfPlanets();
    }
}
