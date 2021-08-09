package com.launchacademy.petadoption.controllers;

import com.launchacademy.petadoption.models.PetSurrenderApplication;
import com.launchacademy.petadoption.services.PetSurrenderApplicationService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SurrenderApiV1RestController {

  private final PetSurrenderApplicationService petSurrenderApplicationService;

  @Autowired
  public SurrenderApiV1RestController(
      PetSurrenderApplicationService petSurrenderApplicationService) {
    this.petSurrenderApplicationService = petSurrenderApplicationService;
  }

  @GetMapping("/surrender")
  public ResponseEntity getSurrenderApplications() {
    List<PetSurrenderApplication> applications = petSurrenderApplicationService
        .getPetSurrenderApplications();

    return new ResponseEntity<>(applications,
        applications.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
  }

  @PostMapping("/surrender")
  public ResponseEntity handleSurrender(@RequestBody Map<String, String> values) {
    return petSurrenderApplicationService.addSurrenderApplication(values);
  }

  @GetMapping("/pending_surrenders")
  public ResponseEntity getPendingSurrenderApplications() {
    List<PetSurrenderApplication> applications = petSurrenderApplicationService
        .getPendingApplications();
    return new ResponseEntity<>(applications,
        applications.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
  }
}
