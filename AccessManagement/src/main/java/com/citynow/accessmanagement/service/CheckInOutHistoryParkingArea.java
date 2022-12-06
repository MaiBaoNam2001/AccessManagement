package com.citynow.accessmanagement.service;

import com.citynow.accessmanagement.service.template.IService;
import java.util.List;
import lombok.Data;

public interface CheckInOutHistoryParkingArea extends
    IService<CheckInOutHistoryParkingArea.Input, CheckInOutHistoryParkingArea.Output> {

  @Data
  class Input {

    private String projectId;
    private String buildingId;
    private String parkingAreaId;
  }

  @Data
  class Output {

    private List<CheckInOutHistoryParkingAreaRecord> checkInOutHistoryParkingAreaRecords;

    @Data
    public static class CheckInOutHistoryParkingAreaRecord {

      private String identityCard;
      private String licensePlate;
      private String vehicleType;
      private String checkType;
      private String checkTime;
    }
  }

  enum CardType {
    MOTORBIKE_CARD, CAR_CARD
  }
}
