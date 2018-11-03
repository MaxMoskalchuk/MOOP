package ua.univ.lab2.TaskB.Stones;

/**
 * Created by Asus on 03.11.2018.
 */
public abstract class GemStonesBuilder<T1 extends GemStonesBuilder<T1 , T2>, T2 extends GemStones> {
    private double _weight;
    private int    _price        = 0;
    private String _name         = "No name";
    private String _manufacturer = "Home-made";
    private String _transparency = "Translucent";

    public GemStonesBuilder() {}

    public T1 setWeight(double _weight) {
        this._weight = _weight;
        return getThis();
    }

    public T1 setPrice(int _price) {
        this._price = _price;
        return getThis();
    }

    public T1 setName(String _name) {
        this._name = _name;
        return getThis();
    }

    public T1 setManufacturer(String _manufacturer) {
        this._manufacturer = _manufacturer;
        return getThis();
    }

    public T1 setTransparency(String _transparency)
    {
        this._transparency = _transparency;
        return  getThis();
    }
    protected double getWeight() { return _weight; }

    protected int getPrice() { return _price; }

    protected String getName() { return _name; }

    protected String getManufacturer() { return _manufacturer; }

    protected  String getTransparency() {return _transparency; }

    protected abstract T1 getThis();

    public abstract T2 build();
}