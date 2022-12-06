package com.citynow.accessmanagement.entity;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class VehicleCard {

  private boolean active;
  private Project project;

  @Data
  public static class Project {

    private String id;
    private Range range;

    @Data
    public static class Range {

      private ParkingArea parking_area;

      @Data
      public static class ParkingArea {

        private String id;
        private List<Control> controls;

        @Data
        public static class Control {

          private String identityCard;
          private String licensePlate;
          private String vehicleType;
          private String checkType;
          private String checkTime;
        }
      }
    }
  }
}