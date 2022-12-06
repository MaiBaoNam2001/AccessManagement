package com.citynow.accessmanagement.filter;

import com.citynow.accessmanagement.entity.ApartmentRegister;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class ApartmentRegisterFilter {

  public static boolean filterByParkingAreaId(ApartmentRegister apartmentRegister,
      String parkingAreaId) {
    String parkingAreaIdByMotorbikeCard = apartmentRegister.getMotorbikeCard().get("project")
        .get("range").get("parking_area").get("id").asText();
    String parkingAreaIdByCarCard = apartmentRegister.getCarCard().get("project").get("range")
        .get("parking_area").get("id").asText();
    boolean isMotorbikeCardActive = apartmentRegister.getMotorbikeCard().get("active").asBoolean();
    boolean isCarCardActive = apartmentRegister.getCarCard().get("active").asBoolean();
    return (Objects.equals(parkingAreaIdByMotorbikeCard, parkingAreaId) && isMotorbikeCardActive)
        || (Objects.equals(parkingAreaIdByCarCard, parkingAreaId) && isCarCardActive);
  }
}
