package com.citynow.accessmanagement.service.impl;

import com.citynow.accessmanagement.common.mapper.ModelConverter;
import com.citynow.accessmanagement.repository.BuildingRepository;
import com.citynow.accessmanagement.service.BuildingFindById;
import com.citynow.accessmanagement.service.template.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuildingFindByIdServiceImpl extends
    BaseService<BuildingFindById.Input, BuildingFindById.Output> implements BuildingFindById {

  private final BuildingRepository buildingRepository;
  private final ModelConverter modelConverter;

  @Override
  protected void preExecute(Input input) {

  }

  @Override
  protected Output doExecute(Input input) {
    return modelConverter.map(buildingRepository.findById(input.getBuildingId()), Output.class);
  }

  @Override
  protected void postExecute(Input input) {

  }
}
