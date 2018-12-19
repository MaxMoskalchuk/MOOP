package ua.univ.lab3.part2;

public class Carrier extends WarShip implements Ship{
    public int weight;

    public String name;

    public int number;

    public Carrier(String name)
    {
        super(name);
    }
    @Override
    public String getName() {
        return "Carrier "+super.getName();
    }

    @Override
    public boolean Move() {
        return false;
    }

    @Override
    public int getWeight() {
        return 0;
    }
}
