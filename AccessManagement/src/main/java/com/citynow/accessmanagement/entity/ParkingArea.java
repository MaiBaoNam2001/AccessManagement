package com.citynow.accessmanagement.entity;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "parking_area")
public class ParkingArea {

  @Id
  @Column(name = "id", nullable = false)
  @Type(type = "org.hibernate.type.TextType")
  private String id;

  @Column(name = "name")
  @Type(type = "org.hibernate.type.TextType")
  private String name;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "building_id")
  private Building building;

  @OneToMany(mappedBy = "parkingArea")
  private Set<ParkingRegister> parkingRegisters = new LinkedHashSet<>();

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Building getBuilding() {
    return building;
  }

  public void setBuilding(Building building) {
    this.building = building;
  }

  public Set<ParkingRegister> getParkingRegisters() {
    return parkingRegisters;
  }

  public void setParkingRegisters(Set<ParkingRegister> parkingRegisters) {
    this.parkingRegisters = parkingRegisters;
  }

}