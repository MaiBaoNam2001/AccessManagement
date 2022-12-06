package com.citynow.accessmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ParkingAreaCheckInOutHistoryController {

  @GetMapping("/parking-area/check-in-out-history")
  public String checkInOutHistoryParkingArea() {
    return "check_in_out_history_parking_area";
  }
}
