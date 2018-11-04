package ua.univ.lab2.TaskB.Stones;

/**
 * Created by Asus on 04.11.2018.
 */
public class HalfPreciousStoneBuilder extends GemStonesBuilder<HalfPreciousStoneBuilder, HalfPreciousStone>
{

    private String _color;
    public HalfPreciousStoneBuilder(){};

    public HalfPreciousStoneBuilder setColor(String _color) {
        this._color = _color;
        return this;
    }


    @Override
    public HalfPreciousStone build() {
        return new HalfPreciousStone(super.getName(),
        super.getPrice(),
        super.getWeight(),
        super.getManufacturer(),
        super.getTransparency(),
        _color);
    }
    @Override
    protected HalfPreciousStoneBuilder getThis() {
        return this;
    }
}
