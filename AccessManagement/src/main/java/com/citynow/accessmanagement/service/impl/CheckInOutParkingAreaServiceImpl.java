package com.citynow.accessmanagement.service.impl;

import com.citynow.accessmanagement.common.mapper.ModelConverter;
import com.citynow.accessmanagement.entity.ApartmentRegister;
import com.citynow.accessmanagement.entity.ParkingRegister;
import com.citynow.accessmanagement.entity.VehicleCard;
import com.citynow.accessmanagement.entity.VehicleCard.Project.Range.ParkingArea.Control;
import com.citynow.accessmanagement.filter.ApartmentRegisterFilter;
import com.citynow.accessmanagement.repository.ApartmentRegisterRepository;
import com.citynow.accessmanagement.repository.ParkingRegisterRepository;
import com.citynow.accessmanagement.service.CheckInOutParkingArea;
import com.citynow.accessmanagement.service.CheckInOutParkingArea.Output.Result;
import com.citynow.accessmanagement.service.template.BaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class CheckInOutParkingAreaServiceImpl extends
    BaseService<CheckInOutParkingArea.Input, CheckInOutParkingArea.Output> implements
    CheckInOutParkingArea {

  private final ApartmentRegisterRepository apartmentRegisterRepository;
  private final ParkingRegisterRepository parkingRegisterRepository;
  private final ModelConverter modelConverter;
  private final ObjectMapper objectMapper;

  @Override
  protected void preExecute(Input input) {

  }

  @Override
  protected Output doExecute(Input input) {
    Output output = new Output();
    List<ApartmentRegister> apartmentRegisters = apartmentRegisterRepository.findByIdentityCard(
        input.getIdentityCard());
    apartmentRegisters = apartmentRegisters.stream().filter(
        apartmentRegister -> ApartmentRegisterFilter.filterByParkingAreaId(apartmentRegister,
            input.getParkingAreaId())).collect(Collectors.toList());
    final boolean notExistApartmentRegister = apartmentRegisters.stream()
        .max(Comparator.comparing(ApartmentRegister::getRegisterDate)).isEmpty();
    if (notExistApartmentRegister) {
      output.setStatusCode(500);
      return output;
    }
    ApartmentRegister apartmentRegister = apartmentRegisters.stream()
        .max(Comparator.comparing(ApartmentRegister::getRegisterDate)).get();

    final boolean notExistLicensePlate = parkingRegisterRepository.findByLicensePlate(
        input.getLicensePlate()).isEmpty();
    if (notExistLicensePlate) {
      output.setStatusCode(500);
      return output;
    }

    ParkingRegister parkingRegister = parkingRegisterRepository.findByLicensePlate(
        input.getLicensePlate()).get();
    final boolean notExistVehicleType = Objects.isNull(parkingRegister.getVehicleType());
    if (notExistVehicleType) {
      output.setStatusCode(500);
      return output;
    }
    if (parkingRegister.getVehicleType().equalsIgnoreCase(MOTORBIKE)) {
      VehicleCard motorbikeCard = this.updateCheckInOutVehicleCard(
          apartmentRegister.getMotorbikeCard(), input, parkingRegister);
      if (Objects.isNull(motorbikeCard)) {
        output.setStatusCode(500);
        return output;
      }
      apartmentRegister.setMotorbikeCard(objectMapper.valueToTree(motorbikeCard));
    } else {
      VehicleCard carCard = this.updateCheckInOutVehicleCard(apartmentRegister.getCarCard(), input,
          parkingRegister);
      if (Objects.isNull(carCard)) {
        output.setStatusCode(500);
        return output;
      }
      apartmentRegister.setCarCard(objectMapper.valueToTree(carCard));
    }
    apartmentRegisterRepository.save(apartmentRegister);
    output.setStatusCode(200);
    output.setResult(modelConverter.map(input, Result.class));
    return output;
  }


  @Override
  protected void postExecute(Input input) {

  }

  private VehicleCard updateCheckInOutVehicleCard(JsonNode vehicleCardJsonNode, Input input,
      ParkingRegister parkingRegister) {
    VehicleCard vehicleCard = null;
    try {
      vehicleCard = objectMapper.treeToValue(vehicleCardJsonNode, VehicleCard.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    if (vehicleCard == null) {
      return null;
    }
    List<Control> controls = ListUtils.emptyIfNull(
        vehicleCard.getProject().getRange().getParking_area().getControls());
    Control control = new Control().setIdentityCard(input.getIdentityCard())
        .setLicensePlate(input.getLicensePlate()).setVehicleType(parkingRegister.getVehicleType())
        .setCheckTime(
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
    if (controls.isEmpty()) {
      control.setCheckType(CHECK_IN);
    } else {
      control.setCheckType(
          controls.get(controls.size() - 1).getCheckType().equals(CHECK_IN) ? CHECK_OUT : CHECK_IN);
    }
    controls.add(control);
    vehicleCard.getProject().getRange().getParking_area().setControls(controls);
    return vehicleCard;
  }
}
