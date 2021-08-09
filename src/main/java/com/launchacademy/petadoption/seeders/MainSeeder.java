package com.launchacademy.petadoption.seeders;

import com.launchacademy.petadoption.services.AdoptablePetService;
import com.launchacademy.petadoption.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MainSeeder implements CommandLineRunner {

  private final PetTypeService petTypeService;
  private final AdoptablePetService adoptablePetService;

  @Autowired
  public MainSeeder(PetTypeService petTypeService, AdoptablePetService adoptablePetService) {
    this.petTypeService = petTypeService;
    this.adoptablePetService = adoptablePetService;
  }

  @Override
  public void run(String... args) throws Exception {
    PetTypeSeeder.seed(petTypeService);
    AdoptablePetSeeder.seed(petTypeService, adoptablePetService);
  }
}
