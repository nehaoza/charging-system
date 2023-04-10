package com.charging.system.exception;

public class InvalidChargeLevelException extends RuntimeException {
  public InvalidChargeLevelException(String message) {
    super(message);
  }
}
