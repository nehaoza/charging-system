package com.charging.system.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.charging.system.domain.ElectronicVehicle;
import com.charging.system.exception.InvalidElectronicVehicleException;
import com.charging.system.exception.InvalidLocalTimeException;
import org.junit.Test;

public class BusDepotServiceTest {

    private final BusDepotService busDepotService = new BusDepotService();

    @Test
    public void shouldThrowExceptionWhenInvalidVehicleProvided() {

        assertThatExceptionOfType(InvalidElectronicVehicleException.class)
                .isThrownBy(() -> busDepotService.getChargeDeferential(null, null))
                .withMessage("Please provide a valid EV");
    }

    @Test
    public void shouldThrowExceptionWhenInvalidLocalTimeProvided() {

        assertThatExceptionOfType(InvalidLocalTimeException.class)
                .isThrownBy(
                        () -> busDepotService.getChargeDeferential(new ElectronicVehicle(30), null))
                .withMessage("Please provide a valid Local Time supplier");
    }
}
