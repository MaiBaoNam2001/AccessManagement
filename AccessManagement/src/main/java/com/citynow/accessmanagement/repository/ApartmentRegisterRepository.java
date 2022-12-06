package com.citynow.accessmanagement.repository;

import com.citynow.accessmanagement.entity.ApartmentRegister;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApartmentRegisterRepository extends JpaRepository<ApartmentRegister, String> {

  @Query("SELECT ar FROM ApartmentRegister ar WHERE ar.customer.identityCard = :identityCard")
  List<ApartmentRegister> findByIdentityCard(@Param("identityCard") String identityCard);

  @Query("SELECT ar FROM ApartmentRegister ar WHERE ar.apartment.floor.building.parkingArea.id = :parkingAreaId")
  List<ApartmentRegister> findByParkingAreaId(@Param("parkingAreaId") String parkingAreaId);
}
