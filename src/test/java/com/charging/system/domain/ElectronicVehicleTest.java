package com.charging.system.domain;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.charging.system.exception.InvalidChargeLevelException;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ElectronicVehicleTest {

    private final int chargeLevel;

    public ElectronicVehicleTest(int chargeLevel) {
        this.chargeLevel = chargeLevel;
    }

    @Parameterized.Parameters
    public static Collection<Object> primeNumbers() {
        return Arrays.asList(new Object[][] {{-1}, {101}});
    }

    @Test
    public void shouldThrowExceptionWhenInvalidChargingLevelProvided() {

        assertThatExceptionOfType(InvalidChargeLevelException.class)
                .isThrownBy(() -> new ElectronicVehicle(chargeLevel))
                .withMessage("Please provide a valid charging level of the vehicle from 0-100.");
    }
}
