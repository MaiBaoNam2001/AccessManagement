package com.citynow.accessmanagement.filter;

import com.citynow.accessmanagement.entity.ApartmentRegister;
import java.util.Objects;
import org.springframework.stereotype.Component;

public class ApartmentRegisterFilter {

  public static boolean filterByParkingAreaId(ApartmentRegister apartmentRegister,
      String parkingAreaId) {
    final String parkingAreaIdByMotorbikeCard = apartmentRegister.getMotorbikeCard().get("project")
        .get("range").get("parking_area").get("id").asText();
    final String parkingAreaIdByCarCard = apartmentRegister.getCarCard().get("project").get("range")
        .get("parking_area").get("id").asText();
    final boolean isMotorbikeCardActive = apartmentRegister.getMotorbikeCard().get("active")
        .asBoolean();
    final boolean isCarCardActive = apartmentRegister.getCarCard().get("active").asBoolean();
    final boolean isParkingAreaIdExistedByMotorbikeCard = Objects.equals(
        parkingAreaIdByMotorbikeCard, parkingAreaId);
    final boolean isParkingAreaIdExistedByCarCard = Objects.equals(parkingAreaIdByCarCard,
        parkingAreaId);
    return (isParkingAreaIdExistedByMotorbikeCard && isMotorbikeCardActive) || (
        isParkingAreaIdExistedByCarCard && isCarCardActive);
  }
}
