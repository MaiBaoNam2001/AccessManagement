package com.citynow.accessmanagement.repository;

import com.citynow.accessmanagement.entity.ParkingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingTypeRepository extends JpaRepository<ParkingType, Long> {

}
