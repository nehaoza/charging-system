package com.charging.system.domain;

import com.charging.system.exception.InvalidChargeLevelException;

public class ElectronicVehicle {
    private int currentCharge;

    public ElectronicVehicle(int currentCharge) {
        if(currentCharge < 0 || currentCharge >100) {
            throw new InvalidChargeLevelException("Please provide a valid charging level of the vehicle from 0-100.");
        }
        this.currentCharge = currentCharge;
    }

    public int currentCharge() {
        return currentCharge;
    }
}
