package com.launchacademy.petadoption.services;

import com.launchacademy.petadoption.models.AdoptablePet;
import com.launchacademy.petadoption.repositories.AdoptablePetRepository;
import com.launchacademy.petadoption.repositories.PetTypeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdoptablePetService {

  private final AdoptablePetRepository adoptablePetRepository;
  private final PetTypeRepository petTypeRepository;

  @Autowired
  public AdoptablePetService(AdoptablePetRepository adoptablePetRepository,
      PetTypeRepository petTypeRepository) {
    this.adoptablePetRepository = adoptablePetRepository;
    this.petTypeRepository = petTypeRepository;
  }

  public List<AdoptablePet> getPets() {
    return (List<AdoptablePet>) this.adoptablePetRepository.findAll();
  }

  public AdoptablePet getPetByTypeAndId(String type, Long id) {
    List<AdoptablePet> pets = this.petTypeRepository.findByTypeIgnoreCase(type).getAdoptablePets();
    return pets.stream()
        .filter(pet -> pet.getId().equals(id)).findFirst().orElse(null);
  }

  public AdoptablePet getPetById(Long id) {
    return this.adoptablePetRepository.findById(id).orElse(null);
  }

  public void addAdoptablePet(AdoptablePet adoptablePet) {
    this.adoptablePetRepository.save(adoptablePet);
  }
}
