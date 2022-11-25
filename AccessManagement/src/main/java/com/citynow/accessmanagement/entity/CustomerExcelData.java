package com.citynow.accessmanagement.entity;

import java.time.LocalDate;
import lombok.Data;

@Data
public class CustomerExcelData {

  private String id;
  private String name;
  private LocalDate dateOfBirth;
  private String gender;
  private String phone;
  private String email;
  private String address;
  private String identityCard;
}
