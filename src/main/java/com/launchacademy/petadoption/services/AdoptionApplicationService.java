package com.launchacademy.petadoption.services;

import com.launchacademy.petadoption.models.AdoptionApplication;
import com.launchacademy.petadoption.repositories.AdoptionApplicationRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdoptionApplicationService {

  private final AdoptionApplicationRepository adoptionApplicationRepository;
  private final AdoptablePetService adoptablePetService;
  private final ValidationService validationService;

  @Autowired
  public AdoptionApplicationService(AdoptionApplicationRepository adoptionApplicationRepository,
      AdoptablePetService adoptablePetService, ValidationService validationService) {
    this.adoptionApplicationRepository = adoptionApplicationRepository;
    this.adoptablePetService = adoptablePetService;
    this.validationService = validationService;
  }

  public List<AdoptionApplication> getAdoptionApplications() {
    return (List<AdoptionApplication>) this.adoptionApplicationRepository.findAll();
  }

  public List<AdoptionApplication> getPendingAdoptionApplications() {
    return this.adoptionApplicationRepository.findAllByApplicationStatusEqualsIgnoreCase("pending");
  }

  public AdoptionApplication getAdoptionApplication(Long id) {
    return this.adoptionApplicationRepository.findById(id).orElse(null);
  }

  public ResponseEntity<Map> addAdoptionApplication(Map<String, String> values) {
    AdoptionApplication application = new AdoptionApplication();

    application
        .setAdoptablePet(this.adoptablePetService.getPetById(Long.parseLong(values.get("petId"))));
    application.setName(values.get("name"));
    application.setPhoneNumber(values.get("phoneNumber"));
    application.setEmail(values.get("email"));
    application.setHomeStatus(values.get("homeStatus"));

    Set<ConstraintViolation<AdoptionApplication>> violations = validationService
        .validateAdoptionApplication(application);
    Map<String, String> errors = new HashMap<>();

    if (!violations.isEmpty()) {
      for (ConstraintViolation<AdoptionApplication> violation : violations) {
        errors.put(violation.getPropertyPath().toString(), violation.getMessage());
      }
      return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    this.adoptionApplicationRepository.save(application);
    return new ResponseEntity<>(errors, HttpStatus.CREATED);
  }

  public ResponseEntity<Map> updateAdoptionApplication(Map<String, String> values) {
    AdoptionApplication application = this.adoptionApplicationRepository
        .findById(Long.parseLong(values.get("id"))).orElse(null);
    Map<String, String> errors = new HashMap<>();

    if (application == null) {
      return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    application.setName(values.get("name"));
    application.setPhoneNumber(values.get("phoneNumber"));
    application.setEmail(values.get("email"));
    application.setHomeStatus(values.get("homeStatus"));

    Set<ConstraintViolation<AdoptionApplication>> violations = validationService
        .validateAdoptionApplication(application);

    if (!violations.isEmpty()) {
      for (ConstraintViolation<AdoptionApplication> violation : violations) {
        errors.put(violation.getPropertyPath().toString(), violation.getMessage());
      }
      return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    this.adoptionApplicationRepository.save(application);
    return new ResponseEntity<>(errors, HttpStatus.OK);
  }

  public void deleteApplicationById(AdoptionApplication application) {
    this.adoptionApplicationRepository.delete(application);
  }
}
