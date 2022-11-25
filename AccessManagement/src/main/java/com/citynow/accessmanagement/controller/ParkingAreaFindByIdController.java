package com.citynow.accessmanagement.controller;

import com.citynow.accessmanagement.common.mapper.ModelConverter;
import com.citynow.accessmanagement.dto.response.ParkingAreaFindByIdResponse;
import com.citynow.accessmanagement.service.ParkingAreaFindById;
import com.citynow.accessmanagement.service.ParkingAreaFindById.Input;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ParkingAreaFindByIdController {

  private final ParkingAreaFindById parkingAreaFindById;
  private final ModelConverter modelConverter;

  @GetMapping("/parking-areas/id/{parkingAreaId}")
  public ParkingAreaFindByIdResponse findParkingAreaById(
      @PathVariable(value = "parkingAreaId") String parkingAreaId) {
    ParkingAreaFindById.Input input = new Input();
    input.setParkingAreaId(parkingAreaId);
    return modelConverter.map(parkingAreaFindById.execute(input),
        ParkingAreaFindByIdResponse.class);
  }
}
