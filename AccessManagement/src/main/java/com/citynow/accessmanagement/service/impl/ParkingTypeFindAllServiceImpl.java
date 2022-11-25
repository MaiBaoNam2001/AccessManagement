package com.citynow.accessmanagement.service.impl;

import com.citynow.accessmanagement.common.mapper.ModelConverter;
import com.citynow.accessmanagement.repository.ParkingTypeRepository;
import com.citynow.accessmanagement.service.ParkingTypeFindAll;
import com.citynow.accessmanagement.service.template.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParkingTypeFindAllServiceImpl extends
    BaseService<ParkingTypeFindAll.Input, ParkingTypeFindAll.Output> implements ParkingTypeFindAll {

  private final ParkingTypeRepository parkingTypeRepository;
  private final ModelConverter modelConverter;

  @Override
  protected void preExecute(Input input) {

  }

  @Override
  protected Output doExecute(Input input) {
    Output output = new Output();
    output.setParkingTypes(
        modelConverter.mapAllByIterator(parkingTypeRepository.findAll(), Output.ParkingType.class));
    return output;
  }

  @Override
  protected void postExecute(Input input) {

  }
}
