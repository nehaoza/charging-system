package com.charging.system.service;

import com.charging.system.domain.CommuterBus;
import com.charging.system.domain.ElectronicVehicle;
import com.charging.system.domain.SchoolBus;
import com.charging.system.exception.InvalidElectronicVehicleException;
import com.charging.system.exception.InvalidLocalTimeException;
import java.time.LocalTime;
import java.util.function.Supplier;

public class BusDepotService {

    public int getChargeDeferential(
            final ElectronicVehicle electronicVehicle, final Supplier<LocalTime> time) {
        validateInput(electronicVehicle, time);

        final var localTime = time.get();

        var charge = 0;

        if (electronicVehicle instanceof SchoolBus) {
            charge = chargeSchoolBus(electronicVehicle.currentCharge(), localTime);
        } else if (electronicVehicle instanceof CommuterBus) {
            charge = chargeCommuterBus(localTime);
        }
        if (charge == 0) {
            charge = chargeEV(electronicVehicle.currentCharge());
        }

        return charge;
    }

    private int chargeEV(final int currentCharge) {
        var charge = 0;
        if (currentCharge < 50) {
            charge = 80;
        } else if (currentCharge >= 50 && currentCharge <= 60) {
            charge = 70;
        } else if (currentCharge > 60) {
            charge = 50;
        }
        return charge;
    }

    private int chargeCommuterBus(final LocalTime localTime) {
        var charge = 0;
        if (timeIsBetween(localTime, "02:59:59", "07:00:00")) {
            charge = 95;
        } else if (localTime.isAfter(LocalTime.parse("11:15:00"))) {
            charge = 30;
        }
        return charge;
    }

    private int chargeSchoolBus(final int currentCharge, final LocalTime localTime) {
        var charge = 0;
        if (localTime.isBefore(LocalTime.parse("08:00:00"))) {
            charge = 90;
        } else if (timeIsBetween(localTime, "08:00:00", "11:00:00")) {
            charge = 50;
        } else if (currentCharge > 50 && timeIsBetween(localTime, "18:00:00", "23:59:59")) {
            charge = 30;
        }
        return charge;
    }

    private boolean timeIsBetween(
            final LocalTime localTime, final String afterHour, final String beforeHour) {
        return localTime.isAfter(LocalTime.parse(afterHour))
                && localTime.isBefore(LocalTime.parse(beforeHour));
    }

    private void validateInput(
            final ElectronicVehicle electronicVehicle, final Supplier<LocalTime> time) {
        if (electronicVehicle == null) {
            throw new InvalidElectronicVehicleException("Please provide a valid EV");
        }
        if (time == null) {
            throw new InvalidLocalTimeException("Please provide a valid Local Time supplier");
        }
    }
}
