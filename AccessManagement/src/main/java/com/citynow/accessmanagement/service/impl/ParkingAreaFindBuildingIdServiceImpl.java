package com.citynow.accessmanagement.service.impl;

import com.citynow.accessmanagement.common.mapper.ModelConverter;
import com.citynow.accessmanagement.repository.ParkingAreaRepository;
import com.citynow.accessmanagement.service.ParkingAreaFindByBuildingId;
import com.citynow.accessmanagement.service.template.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParkingAreaFindBuildingIdServiceImpl extends
    BaseService<ParkingAreaFindByBuildingId.Input, ParkingAreaFindByBuildingId.Output> implements
    ParkingAreaFindByBuildingId {

  private final ParkingAreaRepository parkingAreaRepository;
  private final ModelConverter modelConverter;

  @Override
  protected void preExecute(Input input) {

  }

  @Override
  protected Output doExecute(Input input) {
    Output output = new Output();
    output.setParkingAreas(modelConverter.mapAllByIterator(
        parkingAreaRepository.findByBuildingId(input.getBuildingId()), Output.ParkingArea.class));
    return output;
  }

  @Override
  protected void postExecute(Input input) {

  }
}
