package com.citynow.accessmanagement.controller;

import com.citynow.accessmanagement.common.mapper.ModelConverter;
import com.citynow.accessmanagement.dto.response.BuildingFindByProjectIdResponse;
import com.citynow.accessmanagement.service.BuildingFindByProjectId;
import com.citynow.accessmanagement.service.BuildingFindByProjectId.Input;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BuildingFindByProjectIdController {

  private final BuildingFindByProjectId buildingFindByProjectId;
  private final ModelConverter modelConverter;

  @GetMapping("/buildings/{projectId}")
  public List<BuildingFindByProjectIdResponse> findBuildingsByProjectId(
      @PathVariable(value = "projectId") String projectId) {
    BuildingFindByProjectId.Input input = new Input();
    input.setProjectId(projectId);
    return modelConverter.mapAllByIterator(buildingFindByProjectId.execute(input).getBuildings(),
        BuildingFindByProjectIdResponse.class);
  }
}
