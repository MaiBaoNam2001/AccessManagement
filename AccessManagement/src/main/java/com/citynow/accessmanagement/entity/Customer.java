package com.citynow.accessmanagement.entity;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "customer")
public class Customer {

  @Id
  @Column(name = "id", nullable = false)
  @Type(type = "org.hibernate.type.TextType")
  private String id;

  @Column(name = "name")
  @Type(type = "org.hibernate.type.TextType")
  private String name;

  @Column(name = "date_of_birth")
  private LocalDate dateOfBirth;

  @Column(name = "gender")
  @Type(type = "org.hibernate.type.TextType")
  private String gender;

  @Column(name = "phone")
  @Type(type = "org.hibernate.type.TextType")
  private String phone;

  @Column(name = "email")
  @Type(type = "org.hibernate.type.TextType")
  private String email;

  @Column(name = "address")
  @Type(type = "org.hibernate.type.TextType")
  private String address;

  @Column(name = "type")
  @Type(type = "org.hibernate.type.TextType")
  private String type;

  @Column(name = "identity_card")
  @Type(type = "org.hibernate.type.TextType")
  private String identityCard;

  @Column(name = "is_deleted")
  private Boolean isDeleted;

  @OneToMany(mappedBy = "customer")
  private Set<ApartmentRegister> apartmentRegisters = new LinkedHashSet<>();

  @OneToMany(mappedBy = "customer")
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

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getIdentityCard() {
    return identityCard;
  }

  public void setIdentityCard(String identityCard) {
    this.identityCard = identityCard;
  }

  public Boolean getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(Boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

  public Set<ApartmentRegister> getApartmentRegisters() {
    return apartmentRegisters;
  }

  public void setApartmentRegisters(Set<ApartmentRegister> apartmentRegisters) {
    this.apartmentRegisters = apartmentRegisters;
  }

  public Set<ParkingRegister> getParkingRegisters() {
    return parkingRegisters;
  }

  public void setParkingRegisters(Set<ParkingRegister> parkingRegisters) {
    this.parkingRegisters = parkingRegisters;
  }

}