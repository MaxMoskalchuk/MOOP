package ua.univ.lab2.TaskA;

public abstract class Astronomical_Body {
    private String Name;
    private int Wejght;
    protected Astronomical_Body(String name, int weight)
    {
        this.Name=name;
        this.Wejght = weight;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Astronomical_Body)) return false;
        Astronomical_Body other = (Astronomical_Body) obj;
        String currentName = Name;
        String otherName = other.Name;
        return currentName == otherName;
    }

    @Override
    public int hashCode() {
        long hash = this.Wejght;
        boolean intOverflow = hash > Integer.MAX_VALUE;
        hash = hash ^ (hash << 11);
        hash = hash ^ (hash >> 19) ^ hash ^ (hash >> 8);
        hash %= (long)Integer.MAX_VALUE;
        if(intOverflow) hash = -hash;
        return (int)hash;
    }

    @Override
    public String toString() {
        return "Name: " + Name + ", Weight: " + Wejght;
    }
}