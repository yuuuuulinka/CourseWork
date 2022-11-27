package com.example.homedeviceskursova.device;

public class LargeDomesticDevice extends Device {
    private String type;

    public LargeDomesticDevice(String brand, String name, String serialNumber, int power, String pluggedIn, String enableState, int powerInOffState, String type) {
        super(brand, name, serialNumber, power, pluggedIn, enableState, powerInOffState);
        this.type = type;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    @Override
    public String toString() {
        return  super.toString() + '\n' +
                "\ttype= '" + type + '\'';
    }
}
