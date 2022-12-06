package com.citynow.accessmanagement.topic;

import com.citynow.accessmanagement.common.mapper.ModelConverter;
import com.citynow.accessmanagement.dto.request.CheckInOutParkingAreaTopicRequest;
import com.citynow.accessmanagement.dto.response.CheckInOutParkingAreaTopicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ParkingAreaCheckInOutTopicController {

  private final ModelConverter modelConverter;

  @MessageMapping("/send-check-in-out-parking-area-information")
  @SendTo("/topic/public")
  public CheckInOutParkingAreaTopicResponse sendCheckInOutParkingAreaInformation(
      CheckInOutParkingAreaTopicRequest request) {
    return modelConverter.map(request, CheckInOutParkingAreaTopicResponse.class);
  }
}
