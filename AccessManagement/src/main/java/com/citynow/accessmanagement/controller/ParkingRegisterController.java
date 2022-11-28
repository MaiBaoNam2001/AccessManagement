package com.citynow.accessmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ParkingRegisterController {

  @GetMapping("/parking_register")
  public String registerParking() {
    return "parking_register";
  }
}
