package com.citynow.accessmanagement.controller;

import com.citynow.accessmanagement.common.mapper.ModelConverter;
import com.citynow.accessmanagement.dto.request.CheckInOutHistoryParkingAreaRequest;
import com.citynow.accessmanagement.dto.response.CheckInOutHistoryParkingAreaResponse;
import com.citynow.accessmanagement.service.CheckInOutHistoryParkingArea;
import com.citynow.accessmanagement.service.CheckInOutHistoryParkingArea.Input;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CheckInOutHistoryParkingAreaController {

  private final CheckInOutHistoryParkingArea checkInOutHistoryParkingArea;
  private final ModelConverter modelConverter;

  @PostMapping("/parking-area/check-in-out-history")
  public List<CheckInOutHistoryParkingAreaResponse> getCheckInOutHistoryParkingAreaByParkingAreaId(
      @RequestBody CheckInOutHistoryParkingAreaRequest checkInOutHistoryParkingAreaRequest) {
    CheckInOutHistoryParkingArea.Input input = modelConverter.map(
        checkInOutHistoryParkingAreaRequest, Input.class);
    return modelConverter.mapAllByIterator(
        checkInOutHistoryParkingArea.execute(input).getCheckInOutHistoryParkingAreaRecords(),
        CheckInOutHistoryParkingAreaResponse.class);
  }
}
