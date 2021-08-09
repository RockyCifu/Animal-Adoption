package com.launchacademy.petadoption.services;

import com.launchacademy.petadoption.models.AdoptablePet;
import com.launchacademy.petadoption.models.PetSurrenderApplication;
import com.launchacademy.petadoption.repositories.PetSurrenderApplicationRepository;
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
public class PetSurrenderApplicationService {

  private final PetSurrenderApplicationRepository petSurrenderApplicationRepository;
  private final AdoptablePetService adoptablePetService;
  private final PetTypeService petTypeService;
  private final ValidationService validationService;

  @Autowired
  public PetSurrenderApplicationService(
      PetSurrenderApplicationRepository petSurrenderApplicationRepository,
      AdoptablePetService adoptablePetService, PetTypeService petTypeService,
      ValidationService validationService) {
    this.petSurrenderApplicationRepository = petSurrenderApplicationRepository;
    this.adoptablePetService = adoptablePetService;
    this.petTypeService = petTypeService;
    this.validationService = validationService;
  }

  public List<PetSurrenderApplication> getPetSurrenderApplications() {
    return (List<PetSurrenderApplication>) this.petSurrenderApplicationRepository.findAll();
  }

  public List<PetSurrenderApplication> getPendingApplications() {
    return this.petSurrenderApplicationRepository.findAllByStatusEqualsIgnoreCase("pending");
  }

  public ResponseEntity<Map> addSurrenderApplication(Map<String, String> values) {
    PetSurrenderApplication application = new PetSurrenderApplication();
    AdoptablePet newPet = new AdoptablePet();

    newPet.setName(values.get("petName"));
    newPet.setImgUrl(values.get("imgUrl"));
    newPet.setAge(Integer.parseInt(values.get("petAge")));
    newPet.setAdoptionStory(values.get("adoptionStory"));
    newPet.setVaccinationStatus(Boolean.parseBoolean(values.get("vaccinationStatus")));
    newPet.setPetType(this.petTypeService.getOneByType(values.get("petType")));
    newPet.setPetSurrenderApplication(application);

    application.setName(values.get("name"));
    application.setPhoneNumber(values.get("phoneNumber"));
    application.setEmail(values.get("email"));
    application.setAdoptablePet(newPet);

    Set<ConstraintViolation<AdoptablePet>> petViolations = validationService
        .validateAdoptablePet(newPet);
    Set<ConstraintViolation<PetSurrenderApplication>> applicationViolations = validationService
        .validatePetSurrenderApplication(application);
    Map<String, String> errors = new HashMap<>();

    if (!petViolations.isEmpty() || !applicationViolations.isEmpty()) {
      for (ConstraintViolation<AdoptablePet> violation : petViolations) {
        errors.put(violation.getPropertyPath().toString(), violation.getMessage());
      }
      for (ConstraintViolation<PetSurrenderApplication> violation : applicationViolations) {
        errors.put(violation.getPropertyPath().toString(), violation.getMessage());
      }
      return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    this.adoptablePetService.addAdoptablePet(newPet);
    this.petSurrenderApplicationRepository.save(application);

    return new ResponseEntity<>(errors, HttpStatus.CREATED);
  }
}
