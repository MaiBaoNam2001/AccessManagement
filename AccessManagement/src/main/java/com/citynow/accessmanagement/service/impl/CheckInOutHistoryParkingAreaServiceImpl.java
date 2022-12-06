package com.citynow.accessmanagement.service.impl;

import com.citynow.accessmanagement.common.mapper.ModelConverter;
import com.citynow.accessmanagement.entity.ApartmentRegister;
import com.citynow.accessmanagement.entity.VehicleCard;
import com.citynow.accessmanagement.entity.VehicleCard.Project.Range.ParkingArea.Control;
import com.citynow.accessmanagement.repository.ApartmentRegisterRepository;
import com.citynow.accessmanagement.service.CheckInOutHistoryParkingArea;
import com.citynow.accessmanagement.service.CheckInOutHistoryParkingArea.Output.CheckInOutHistoryParkingAreaRecord;
import com.citynow.accessmanagement.service.template.BaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckInOutHistoryParkingAreaServiceImpl extends
    BaseService<CheckInOutHistoryParkingArea.Input, CheckInOutHistoryParkingArea.Output> implements
    CheckInOutHistoryParkingArea {

  private final ApartmentRegisterRepository apartmentRegisterRepository;
  private final ModelConverter modelConverter;
  private final ObjectMapper objectMapper;
  private final Environment env;

  @Override
  protected void preExecute(Input input) {

  }

  @Override
  protected Output doExecute(Input input) {
    List<ApartmentRegister> apartmentRegisters = ListUtils.emptyIfNull(
        apartmentRegisterRepository.findByParkingAreaId(input.getParkingAreaId()));

    List<CheckInOutHistoryParkingArea.Output.CheckInOutHistoryParkingAreaRecord> checkInOutHistoryParkingAreaRecords = new ArrayList<>();

    List<VehicleCard> motorbikeCards = this.getVehicleCardsByCardType(apartmentRegisters,
        CardType.MOTORBIKE_CARD);
    List<List<Control>> motorbikeCardControlLists = this.getVehicleCardControlLists(motorbikeCards);
    for (List<Control> motorbikeCardControlList : motorbikeCardControlLists) {
      checkInOutHistoryParkingAreaRecords.addAll(
          modelConverter.mapAllByIterator(motorbikeCardControlList,
              CheckInOutHistoryParkingAreaRecord.class));
    }

    List<VehicleCard> carCards = this.getVehicleCardsByCardType(apartmentRegisters,
        CardType.CAR_CARD);
    List<List<Control>> carCardControlLists = this.getVehicleCardControlLists(carCards);
    for (List<Control> carCardControlList : carCardControlLists) {
      checkInOutHistoryParkingAreaRecords.addAll(modelConverter.mapAllByIterator(carCardControlList,
          CheckInOutHistoryParkingAreaRecord.class));
    }
    checkInOutHistoryParkingAreaRecords = checkInOutHistoryParkingAreaRecords.stream()
        .sorted(Comparator.comparing(CheckInOutHistoryParkingAreaRecord::getCheckTime).reversed())
        .collect(Collectors.toList());

    Output output = new Output();
    output.setCheckInOutHistoryParkingAreaRecords(checkInOutHistoryParkingAreaRecords);
    return output;
  }

  @Override
  protected void postExecute(Input input) {

  }

  private List<VehicleCard> getVehicleCardsByCardType(List<ApartmentRegister> apartmentRegisters,
      CardType cardType) {
    return ListUtils.emptyIfNull(apartmentRegisters.stream().map(
        cardType.equals(CardType.MOTORBIKE_CARD) ? ApartmentRegister::getMotorbikeCard
            : ApartmentRegister::getCarCard).map(jsonNode -> {
      try {
        return objectMapper.treeToValue(jsonNode, VehicleCard.class);
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
      return null;
    }).collect(Collectors.toList()));
  }

  private List<List<Control>> getVehicleCardControlLists(List<VehicleCard> vehicleCards) {
    return ListUtils.emptyIfNull(vehicleCards.stream()
        .map(vehicleCard -> vehicleCard.getProject().getRange().getParking_area().getControls())
        .collect(Collectors.toList()));
  }
}
