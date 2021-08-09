package com.launchacademy.petadoption.controllers;

import com.launchacademy.petadoption.models.AdoptionApplication;
import com.launchacademy.petadoption.services.AdoptionApplicationService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class AdoptionApplicationsApiV1RestController {

  private final AdoptionApplicationService adoptionApplicationService;

  @Autowired
  public AdoptionApplicationsApiV1RestController(
      AdoptionApplicationService adoptionApplicationService) {
    this.adoptionApplicationService = adoptionApplicationService;
  }

  @GetMapping("/application")
  public ResponseEntity getAdoptionApplications() {
    List<AdoptionApplication> applications = adoptionApplicationService.getAdoptionApplications();
    return new ResponseEntity<>(applications,
        applications.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
  }

  @PostMapping("/application")
  public ResponseEntity addApplicationForPet(@RequestBody Map<String, String> values) {
    return adoptionApplicationService.addAdoptionApplication(values);
  }

  @PatchMapping("/application")
  public ResponseEntity updateApplication(@RequestBody Map<String, String> values) {
    return adoptionApplicationService.updateAdoptionApplication(values);
  }

  @DeleteMapping("/application")
  public ResponseEntity deleteApplication(@RequestBody Map<String, String> values) {
    AdoptionApplication application = adoptionApplicationService
        .getAdoptionApplication(Long.parseLong(values.get("id")));
    if (application == null) {
      return new ResponseEntity<>("No application found", HttpStatus.NOT_FOUND);
    }
    adoptionApplicationService.deleteApplicationById(application);
    return new ResponseEntity<>("Application Deleted", HttpStatus.OK);
  }

  @GetMapping("/pending_applications")
  public ResponseEntity getPendingAdoptionApplications() {
    List<AdoptionApplication> applications = adoptionApplicationService
        .getPendingAdoptionApplications();
    return new ResponseEntity<>(applications,
        applications.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
  }
}
