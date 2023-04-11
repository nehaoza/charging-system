Feature: Test Bus Depot Service to charge or discharge Electric Vehicles based on current charge level, arrival time and vehicle type

  @AnyEV
  Scenario Outline: Charge any Electric Vehicle with current charging level between 0% - 60% with less than 50% to 80%
    Given the Bus Depot Service is running
    And the "Electric Vehicle" with a charge level of <current-charge-level>% arrives for charging
    When the vehicle is connected to the charging station
    Then the vehicle should be charged to <expected-charge>%
    Examples:
      | current-charge-level | expected-charge |
      | 40                   | 80              |
      | 49                   | 80              |
      | 50                   | 70              |
      | 51                   | 70              |
      | 60                   | 70              |
      | 61                   | 50              |

  @CommuterBus
  Scenario Outline: Charge Commuter Bus with 95% if connected between 3.00 AM to 7.00 AM
    Given the Bus Depot Service is running
    And the "Commuter Bus" arrives for charging
    When the vehicle is connected to the charging station at "<arrival-time>"
    Then the vehicle should be charged to 95%
    Examples:
      | arrival-time |
      | 03:00:00     |
      | 03:00:59     |
      | 06:59:59     |

  @CommuterBus
  Scenario Outline: Discharge Commuter Bus to 30% if connected between 11.15 pm to midnight
    Given the Bus Depot Service is running
    And the "Commuter Bus" arrives for charging
    When the vehicle is connected to the charging station at "<arrival-time>"
    Then the vehicle should be discharged to 30%
    Examples:
      | arrival-time |
      | 23:15:00     |
      | 23:30:59     |
      | 23:59:59     |

  @CommuterBus
  Scenario Outline: Charge or Discharge Commuter Bus as per any other EV if connected after 12.00 AM to 3.00 AM or 7.00 AM to 11.15 PM
    Given the Bus Depot Service is running
    And the "Commuter Bus" with a charge level of <current-charge-level>% arrives for charging
    When the vehicle is connected to the charging station at "<arrival-time>"
    Then the vehicle should be "<charging-type>" to <expected-charge>%
    Examples:
      | arrival-time | current-charge-level | expected-charge | charging-type |
      | 00:00:01     | 49                   | 80              | charged       |
      | 02:00:00     | 50                   | 70              | charged       |
      | 02:59:59     | 61                   | 50              | discharged    |
      | 07:00:01     | 49                   | 80              | charged       |
      | 08:00:00     | 50                   | 70              | charged       |
      | 11:14:59     | 61                   | 50              | discharged    |

    @SchoolBus
  Scenario Outline: Charge School Bus with 90% if connected before 8.00 AM (from midnight to 8.00 AM)
    Given the Bus Depot Service is running
    And the "School Bus" arrives for charging
    When the vehicle is connected to the charging station at "<arrival-time>"
    Then the vehicle should be discharged to 90%
    Examples:
      | arrival-time |
      | 00:00:00     |
      | 04:00:00     |
      | 07:59:59     |

  @SchoolBus
  Scenario Outline: Discharge School Bus with 50% if connected after 8.00 AM and before 11.00 AM
    Given the Bus Depot Service is running
    And the "School Bus" arrives for charging
    When the vehicle is connected to the charging station at "<arrival-time>"
    Then the vehicle should be discharged to 50%
    Examples:
      | arrival-time |
      | 08:00:01     |
      | 10:59:59     |

  @SchoolBus
  Scenario Outline: Charge or Discharge School Bus as per any other EV if connected after 11.00 AM till midnight
    Given the Bus Depot Service is running
    And the "School Bus" with a charge level of <current-charge-level>% arrives for charging
    When the vehicle is connected to the charging station at "<arrival-time>"
    Then the vehicle should be "<charging-type>" to <expected-charge>%
    Examples:
      | arrival-time | current-charge-level | expected-charge | charging-type |
      | 11:00:01     | 49                   | 80              | charged       |
      | 14:00:00     | 50                   | 70              | charged       |
      | 11:59:59     | 61                   | 50              | discharged    |

  @SchoolBus
  Scenario Outline: Discharge School Bus to 30% if current charge level is above 50% and if connected after 6.00 PM and before 12.00 AM
    Given the Bus Depot Service is running
    And the "School Bus" with a charge level of <current-charge-level>% arrives for charging
    When the vehicle is connected to the charging station at "<arrival-time>"
    Then the vehicle should be discharged to 30%
    Examples:
      | arrival-time | current-charge-level |
      | 18:00:01     | 51                   |
      | 23:59:58     | 80                   |
