package com.charging.system.domain;

import com.charging.system.exception.InvalidChargeLevelException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(Parameterized.class)
public class ElectronicVehicleTest {

  private int chargeLevel;

  public ElectronicVehicleTest(int chargeLevel) {
    this.chargeLevel = chargeLevel;
  }

  @Parameterized.Parameters
  public static Collection<Object> primeNumbers() {
    return Arrays.asList(new Object[][] {
        { -1 },
        { 101 }
    });
  }

  @Test
  public void shouldThrowExceptionWhenInvalidChargingLevelProvided() {

    assertThatExceptionOfType(InvalidChargeLevelException.class)
        .isThrownBy(() -> new ElectronicVehicle(chargeLevel))
        .withMessage("Please provide a valid charging level of the vehicle from 0-100.");
  }

}