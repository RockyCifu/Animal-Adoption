package com.launchacademy.petadoption.services;

import com.launchacademy.petadoption.models.AdoptablePet;
import com.launchacademy.petadoption.models.PetSurrenderApplication;
import com.launchacademy.petadoption.models.PetType;
import com.launchacademy.petadoption.repositories.PetTypeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PetTypeService {

  private final PetTypeRepository petTypeRepository;

  public PetTypeService(PetTypeRepository petTypeRepository) {
    this.petTypeRepository = petTypeRepository;
  }

  public List<PetType> getPetTypes() {
    return (List<PetType>) this.petTypeRepository.findAll();
  }

  public List<AdoptablePet> getPetsByType(String type) {
    List<PetType> types = this.petTypeRepository.findAllByTypeIgnoreCase(type);
    List<AdoptablePet> pets = new ArrayList<>();

    if (!types.isEmpty()) {
      for (AdoptablePet pet : types.get(0).getAdoptablePets()) {
        PetSurrenderApplication application = pet.getPetSurrenderApplication();
        if (application == null || application.getStatus().equals("Approved")) {
          pets.add(pet);
        }
      }
    }
    return pets;
  }

  public PetType getOneByType(String type) {
    return this.petTypeRepository.findByType(type);
  }

  public void addPetType(PetType petType) {
    this.petTypeRepository.save(petType);
  }
}
