package com.charging.system.domain;

public class ElectronicVehicle {
    private int currentCharge;

    public ElectronicVehicle(int currentCharge) {
        this.currentCharge = currentCharge;
    }

    public int currentCharge() {
        return currentCharge;
    }
}
