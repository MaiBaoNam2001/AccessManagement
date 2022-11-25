package com.citynow.accessmanagement.controller;

import com.citynow.accessmanagement.common.mapper.ModelConverter;
import com.citynow.accessmanagement.dto.response.ParkingAreaFindBuildingIdResponse;
import com.citynow.accessmanagement.service.ParkingAreaFindByBuildingId;
import com.citynow.accessmanagement.service.ParkingAreaFindByBuildingId.Input;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ParkingAreaFindByBuildingIdController {

  private final ParkingAreaFindByBuildingId parkingAreaFindByBuildingId;
  private final ModelConverter modelConverter;

  @GetMapping("/parking-areas/{buildingId}")
  public List<ParkingAreaFindBuildingIdResponse> findParkingAreasByBuildingId(
      @PathVariable(value = "buildingId") String buildingId) {
    ParkingAreaFindByBuildingId.Input input = new Input();
    input.setBuildingId(buildingId);
    return modelConverter.mapAllByIterator(
        parkingAreaFindByBuildingId.execute(input).getParkingAreas(),
        ParkingAreaFindBuildingIdResponse.class);
  }
}
