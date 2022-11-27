package com.example.homedeviceskursova.device;

public class LDDWithEEClass extends LargeDomesticDevice {
    private String ClassEC;

    public LDDWithEEClass(String brand, String name, String serialNumber, int power, String pluggedIn, String enableState, int powerInOffState, String type, String classEC) {
        super(brand, name, serialNumber, power, pluggedIn, enableState, powerInOffState, type);
        ClassEC = classEC;
    }

    public String getClassEC() { return ClassEC; }

    public void setClassEC(String classEC) { ClassEC = classEC; }

    @Override
    public String toString() {
        return super.toString() +
                "\n\tClassEC= '" + ClassEC + '\'';
    }
}
