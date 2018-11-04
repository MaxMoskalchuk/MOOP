package ua.univ.lab2.TaskB.Stones;

/**
 * Created by Asus on 03.11.2018.
 */
public final class PreciousStoneBuilder extends GemStonesBuilder<PreciousStoneBuilder, PreciousStone> {

    private int _hardness = 5;
    private String _color;
    public PreciousStoneBuilder(){};

    public PreciousStoneBuilder setColor(String _color) {
        this._color = _color;
        return this;
    }

    public PreciousStoneBuilder setHardness(int _hardness) {
        this._hardness = _hardness;
        return this;
    }

    @Override
    public PreciousStone build() {
        return new PreciousStone(super.getName(),
                super.getPrice(),
                super.getWeight(),
                super.getManufacturer(),
                super.getTransparency(),
                _hardness,
                _color);
    }
    @Override
    protected PreciousStoneBuilder getThis() {
        return this;
    }
}
