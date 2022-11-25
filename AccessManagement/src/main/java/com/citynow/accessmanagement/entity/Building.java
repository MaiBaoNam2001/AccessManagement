package com.citynow.accessmanagement.entity;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "building")
public class Building {

  @Id
  @Column(name = "id", nullable = false)
  @Type(type = "org.hibernate.type.TextType")
  private String id;

  @Column(name = "name")
  @Type(type = "org.hibernate.type.TextType")
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "project_id")
  private Project project;

  @OneToOne(mappedBy = "building")
  private ParkingArea parkingArea;

  @OneToMany(mappedBy = "building")
  private Set<Floor> floors = new LinkedHashSet<>();

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

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public ParkingArea getParkingArea() {
    return parkingArea;
  }

  public void setParkingArea(ParkingArea parkingArea) {
    this.parkingArea = parkingArea;
  }

  public Set<Floor> getFloors() {
    return floors;
  }

  public void setFloors(Set<Floor> floors) {
    this.floors = floors;
  }

}