package com.example.homedeviceskursova.device;

public class Device {
    private String brand;
    private String name;
    private String serialNumber;
    private int power;
    private boolean pluggedIn;
    private boolean enableState;
    private int powerInOffState;

    public Device(String brand, String name, String serialNumber, int power, String pluggedIn, String enableState, int powerInOffState) {
        this.brand = brand;
        this.name = name;
        this.serialNumber = serialNumber;
        this.power = power;
        if(enableState.indexOf('n') >= 0)
            this.enableState = true;
        else
            this.enableState = false;
        if(pluggedIn.indexOf('n') >= 0)
            this.pluggedIn = true;
        else
            this.pluggedIn = false;
        this.powerInOffState = powerInOffState;
    }

    public String getBrand() { return brand; }

    public void setBrand(String brand) { this.brand = brand; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSerialNumber() { return serialNumber; }

    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public int getPower() { return power; }

    public void setPower(int power) { this.power = power; }

    public boolean getPluggedIn() { return pluggedIn; }
    public String getPluggedInOnOff() {
        if(pluggedIn)
            return "On";
        else
            return "Off";
    }
    public void setPluggedIn(boolean pluggedIn) { this.pluggedIn = pluggedIn; }

    public boolean getEnableState() { return enableState; }
    public String getEnableStateOnOff() {
        if(enableState)
            return "On";
        else
            return "Off";
    }

    public void setEnableState(boolean enableState) { this.enableState = enableState; }

    public int getPowerInOffState() { return powerInOffState; }

    public void setPowerInOffState(int powerInOffState) { this.powerInOffState = powerInOffState; }

    @Override
    public String toString() {
        return  "\tname= '" + name + "\'\n" +
                "\tbrand= '" + brand + "\'\n" +
                "\tserialNumber= '" + serialNumber + "\'\n" +
                "\tpower= " + power + " Вт\n" +
                "\tpluggedIn= " + getPluggedInOnOff() +
                "\n\tenableState= " + getEnableStateOnOff() +
                "\n\tpowerInOffState= " + powerInOffState + " Вт";
    }

}
