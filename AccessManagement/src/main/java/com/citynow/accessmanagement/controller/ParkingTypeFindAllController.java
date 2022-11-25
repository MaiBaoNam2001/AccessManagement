package com.citynow.accessmanagement.controller;

import com.citynow.accessmanagement.common.mapper.ModelConverter;
import com.citynow.accessmanagement.dto.response.ParkingTypeFindAllResponse;
import com.citynow.accessmanagement.service.ParkingTypeFindAll;
import com.citynow.accessmanagement.service.ParkingTypeFindAll.Input;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ParkingTypeFindAllController {

  private final ParkingTypeFindAll parkingTypeFindAll;
  private final ModelConverter modelConverter;

  @GetMapping("/parking-types")
  public List<ParkingTypeFindAllResponse> findAllParkingTypes() {
    ParkingTypeFindAll.Input input = new Input();
    return modelConverter.mapAllByIterator(parkingTypeFindAll.execute(input).getParkingTypes(),
        ParkingTypeFindAllResponse.class);
  }
}
