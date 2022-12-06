package com.citynow.accessmanagement.repository;

import com.citynow.accessmanagement.entity.ParkingRegister;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRegisterRepository extends JpaRepository<ParkingRegister, String> {

  Optional<ParkingRegister> findByLicensePlate(String licensePlate);
}
