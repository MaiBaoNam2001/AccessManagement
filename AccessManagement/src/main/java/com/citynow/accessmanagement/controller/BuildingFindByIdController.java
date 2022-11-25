package com.citynow.accessmanagement.controller;

import com.citynow.accessmanagement.common.mapper.ModelConverter;
import com.citynow.accessmanagement.dto.response.BuildingFindByIdResponse;
import com.citynow.accessmanagement.service.BuildingFindById;
import com.citynow.accessmanagement.service.BuildingFindById.Input;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BuildingFindByIdController {

  private final BuildingFindById buildingFindById;
  private final ModelConverter modelConverter;

  @GetMapping("/buildings/id/{buildingId}")
  public BuildingFindByIdResponse findBuildingById(
      @PathVariable(value = "buildingId") String buildingId) {
    BuildingFindById.Input input = new Input();
    input.setBuildingId(buildingId);
    return modelConverter.map(buildingFindById.execute(input), BuildingFindByIdResponse.class);
  }
}
