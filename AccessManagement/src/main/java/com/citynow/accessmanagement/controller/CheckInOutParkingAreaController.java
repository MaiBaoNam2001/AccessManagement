package com.citynow.accessmanagement.controller;

import com.citynow.accessmanagement.common.mapper.ModelConverter;
import com.citynow.accessmanagement.dto.request.CheckInOutParkingAreaRequest;
import com.citynow.accessmanagement.dto.response.CheckInOutParkingAreaResponse;
import com.citynow.accessmanagement.service.CheckInOutParkingArea;
import com.citynow.accessmanagement.service.CheckInOutParkingArea.Input;
import com.citynow.accessmanagement.service.CheckInOutParkingArea.Output;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CheckInOutParkingAreaController {

  private final CheckInOutParkingArea checkInOutParkingArea;
  private final ModelConverter modelConverter;

  @PostMapping("/parking-area/check-in-out")
  public CheckInOutParkingAreaResponse checkInOutParkingArea(
      @RequestBody CheckInOutParkingAreaRequest checkInOutParkingAreaRequest) {
    {
      CheckInOutParkingArea.Input input = modelConverter.map(checkInOutParkingAreaRequest,
          Input.class);
      return modelConverter.map(checkInOutParkingArea.execute(input),
          CheckInOutParkingAreaResponse.class);
    }

  }
}
