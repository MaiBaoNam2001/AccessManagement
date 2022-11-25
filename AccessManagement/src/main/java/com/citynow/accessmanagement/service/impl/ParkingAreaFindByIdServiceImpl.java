package com.citynow.accessmanagement.service.impl;

import com.citynow.accessmanagement.common.mapper.ModelConverter;
import com.citynow.accessmanagement.repository.ParkingAreaRepository;
import com.citynow.accessmanagement.service.ParkingAreaFindById;
import com.citynow.accessmanagement.service.template.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParkingAreaFindByIdServiceImpl extends
    BaseService<ParkingAreaFindById.Input, ParkingAreaFindById.Output> implements
    ParkingAreaFindById {

  private final ParkingAreaRepository parkingAreaRepository;
  private final ModelConverter modelConverter;

  @Override
  protected void preExecute(Input input) {

  }

  @Override
  protected Output doExecute(Input input) {
    return modelConverter.map(
        parkingAreaRepository.findById(input.getParkingAreaId()).orElseThrow(), Output.class);
  }

  @Override
  protected void postExecute(Input input) {

  }
}
