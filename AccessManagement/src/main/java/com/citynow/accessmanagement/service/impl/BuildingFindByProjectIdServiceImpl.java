package com.citynow.accessmanagement.service.impl;

import com.citynow.accessmanagement.common.mapper.ModelConverter;
import com.citynow.accessmanagement.repository.BuildingRepository;
import com.citynow.accessmanagement.service.BuildingFindByProjectId;
import com.citynow.accessmanagement.service.template.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuildingFindByProjectIdServiceImpl extends
    BaseService<BuildingFindByProjectId.Input, BuildingFindByProjectId.Output> implements
    BuildingFindByProjectId {

  private final BuildingRepository buildingRepository;
  private final ModelConverter modelConverter;

  @Override
  protected void preExecute(Input input) {

  }

  @Override
  protected Output doExecute(Input input) {
    Output output = new Output();
    output.setBuildings(
        modelConverter.mapAllByIterator(buildingRepository.findByProjectId(input.getProjectId()),
            Output.Building.class));
    return output;
  }

  @Override
  protected void postExecute(Input input) {

  }
}
