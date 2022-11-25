package com.citynow.accessmanagement.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "parking_register")
public class ParkingRegister {

  @Id
  @Column(name = "id", nullable = false)
  @Type(type = "org.hibernate.type.TextType")
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parking_area_id")
  private ParkingArea parkingArea;

  @Column(name = "license_plate")
  @Type(type = "org.hibernate.type.TextType")
  private String licensePlate;

  @Column(name = "brand_name")
  @Type(type = "org.hibernate.type.TextType")
  private String brandName;

  @Column(name = "color")
  @Type(type = "org.hibernate.type.TextType")
  private String color;

  @Column(name = "vehicle_type")
  @Type(type = "org.hibernate.type.TextType")
  private String vehicleType;

  @Column(name = "register_date")
  private LocalDate registerDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parking_type_id")
  private ParkingType parkingType;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public ParkingArea getParkingArea() {
    return parkingArea;
  }

  public void setParkingArea(ParkingArea parkingArea) {
    this.parkingArea = parkingArea;
  }

  public String getLicensePlate() {
    return licensePlate;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  public String getBrandName() {
    return brandName;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getVehicleType() {
    return vehicleType;
  }

  public void setVehicleType(String vehicleType) {
    this.vehicleType = vehicleType;
  }

  public LocalDate getRegisterDate() {
    return registerDate;
  }

  public void setRegisterDate(LocalDate registerDate) {
    this.registerDate = registerDate;
  }

  public ParkingType getParkingType() {
    return parkingType;
  }

  public void setParkingType(ParkingType parkingType) {
    this.parkingType = parkingType;
  }

}