
package ua.univ.lab8.Data.Enums;

public enum ServiceInfo {
    RELAXATION (1),
    EXCURSION (2),
    SHOPPING (4),
    HOT(8);

    private final int value;
    private ServiceInfo(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}