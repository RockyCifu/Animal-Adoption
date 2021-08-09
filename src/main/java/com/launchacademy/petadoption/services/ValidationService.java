package com.launchacademy.petadoption.services;

import com.launchacademy.petadoption.models.AdoptablePet;
import com.launchacademy.petadoption.models.AdoptionApplication;
import com.launchacademy.petadoption.models.PetSurrenderApplication;
import com.launchacademy.petadoption.models.PetType;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

  private final Validator validator;

  @Autowired
  public ValidationService(Validator validator) {
    this.validator = validator;
  }

  public Set<ConstraintViolation<AdoptablePet>> validateAdoptablePet(AdoptablePet adoptablePet) {
    return validator.validate(adoptablePet);
  }

  public Set<ConstraintViolation<AdoptionApplication>> validateAdoptionApplication(
      AdoptionApplication adoptionApplication) {
    return validator.validate(adoptionApplication);
  }

  public Set<ConstraintViolation<PetType>> validatePetType(PetType petType) {
    return validator.validate(petType);
  }

  public Set<ConstraintViolation<PetSurrenderApplication>> validatePetSurrenderApplication(
      PetSurrenderApplication petSurrenderApplication) {
    return validator.validate(petSurrenderApplication);
  }
}
