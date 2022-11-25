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
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "floor")
public class Floor {

  @Id
  @Column(name = "id", nullable = false)
  @Type(type = "org.hibernate.type.TextType")
  private String id;

  @Column(name = "number")
  private Integer number;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "building_id")
  private Building building;

  @OneToMany(mappedBy = "floor")
  private Set<Apartment> apartments = new LinkedHashSet<>();

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public Building getBuilding() {
    return building;
  }

  public void setBuilding(Building building) {
    this.building = building;
  }

  public Set<Apartment> getApartments() {
    return apartments;
  }

  public void setApartments(Set<Apartment> apartments) {
    this.apartments = apartments;
  }

}