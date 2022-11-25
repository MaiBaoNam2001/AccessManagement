package com.citynow.accessmanagement.repository;

import com.citynow.accessmanagement.entity.ParkingArea;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingAreaRepository extends JpaRepository<ParkingArea, String> {

  @Query("SELECT pa FROM ParkingArea pa WHERE pa.building.id = :buildingId")
  public List<ParkingArea> findByBuildingId(@Param("buildingId") String buildingId);
}
