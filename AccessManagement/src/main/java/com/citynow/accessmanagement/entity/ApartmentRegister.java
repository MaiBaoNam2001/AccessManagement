package com.citynow.accessmanagement.entity;

import com.fasterxml.jackson.databind.JsonNode;
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
@Table(name = "apartment_register")
public class ApartmentRegister {

  @Id
  @Column(name = "id", nullable = false)
  @Type(type = "org.hibernate.type.TextType")
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "apartment_id")
  private Apartment apartment;

  @Column(name = "is_host")
  private Boolean isHost;

  @Column(name = "resident_card")
  @Type(type = "com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType")
  private JsonNode residentCard;

  @Column(name = "motorbike_card")
  @Type(type = "com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType")
  private JsonNode motorbikeCard;

  @Column(name = "car_card")
  @Type(type = "com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType")
  private JsonNode carCard;

  @Column(name = "register_date")
  private LocalDate registerDate;

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

  public Apartment getApartment() {
    return apartment;
  }

  public void setApartment(Apartment apartment) {
    this.apartment = apartment;
  }

  public Boolean getIsHost() {
    return isHost;
  }

  public void setIsHost(Boolean isHost) {
    this.isHost = isHost;
  }

  public JsonNode getResidentCard() {
    return residentCard;
  }

  public void setResidentCard(JsonNode residentCard) {
    this.residentCard = residentCard;
  }

  public JsonNode getMotorbikeCard() {
    return motorbikeCard;
  }

  public void setMotorbikeCard(JsonNode motorbikeCard) {
    this.motorbikeCard = motorbikeCard;
  }

  public JsonNode getCarCard() {
    return carCard;
  }

  public void setCarCard(JsonNode carCard) {
    this.carCard = carCard;
  }

  public LocalDate getRegisterDate() {
    return registerDate;
  }

  public void setRegisterDate(LocalDate registerDate) {
    this.registerDate = registerDate;
  }

}