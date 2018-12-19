package ua.univ.lab3.part2;

public abstract class WarShip implements Ship {
    public int weight;

    public String name;


    public WarShip(String name)
    {
        this.name= name;
    }
    @Override
    public String getName() {
        return this.name;
    }
}
