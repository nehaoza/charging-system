package com.charging.system.steps;

import com.charging.system.actions.BusDepotActions;
import com.charging.system.exception.InvalidElectronicVehicleException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalTime;

public class BusDepotSteps {

  private final BusDepotActions actions;

  public BusDepotSteps(BusDepotActions actions) {
    this.actions = actions;
  }

  @Given("the Bus Depot Service is running")
  public void the_bus_depot_service_is_running() {
    actions.createBusDepotService();
  }

  @Given("the {string} with a charge level of {int}% arrives for charging")
  public void an_electric_vehicle_ev_with_a_charge_level_of_arrives_for_charging(String vehicleType, int currentChargingLevel) {
    actions.buildElectricVehicle(vehicleType, currentChargingLevel);
  }

  @Given("the {string} arrives for charging")
  public void the_arrives_for_charging(String vehicleType) {
    actions.buildElectricVehicle(vehicleType, 50);
  }

  @When("the vehicle is connected to the charging station at {string}")
  public void the_is_connected_to_the_charging_station_at(String connectionTime) {
    actions.chargeVehicle(LocalTime.parse(connectionTime));
  }

  @When("the vehicle is connected to the charging station")
  public void the_ev_is_connected_to_the_charging_station() throws InvalidElectronicVehicleException {
    actions.chargeVehicle(LocalTime.now());
  }

  @Then("the vehicle should be charged to {int}%")
  public void the_ev_should_be_charged_to(Integer expectedChargingLevel) {
    actions.assertChargingLevel(expectedChargingLevel);
  }

  @Then("the vehicle should be discharged to {int}%")
  public void the_ev_should_be_discharged_to(Integer expectedChargingLevel) {
    actions.assertChargingLevel(expectedChargingLevel);
  }

  @Then("the vehicle should be {string} to {int}%")
  public void the_vehicle_should_be_to(String string, Integer expectedChargingLevel) {
    actions.assertChargingLevel(expectedChargingLevel);
  }
}
