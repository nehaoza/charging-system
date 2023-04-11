package com.charging.system.actions;

import com.charging.system.domain.CommuterBus;
import com.charging.system.domain.ElectronicVehicle;
import com.charging.system.domain.SchoolBus;
import com.charging.system.service.BusDepotService;
import org.assertj.core.api.Assertions;

import java.time.LocalTime;

public class BusDepotActions {

  private BusDepotService busDepotService;
  private ElectronicVehicle electronicVehicle;
  private Integer chargeLevel;

  public void buildElectricVehicle(String vehicleType, int currentChargingLevel) {
    electronicVehicle = buildElectricVehicleBasedOnType(vehicleType, currentChargingLevel);
  }

  private ElectronicVehicle buildElectricVehicleBasedOnType(String vehicleType, int currentChargingLevel) {
    return switch (vehicleType) {
      case "Electric Vehicle" -> new ElectronicVehicle(currentChargingLevel);
      case "School Bus" -> new SchoolBus(currentChargingLevel);
      case "Commuter Bus" -> new CommuterBus(currentChargingLevel);
      default -> throw new IllegalArgumentException("Please prove a valid Electric Vehicle type");
    };
  }

  public void chargeVehicle(LocalTime connectionTime) {
    chargeLevel = busDepotService.getChargeDeferential(electronicVehicle, () -> connectionTime);
  }

  public void assertChargingLevel(Integer expectedChargingLevel) {
    Assertions.assertThat(chargeLevel).isEqualTo(expectedChargingLevel);
  }

  public void createBusDepotService() {
    busDepotService = new BusDepotService();
  }
}
