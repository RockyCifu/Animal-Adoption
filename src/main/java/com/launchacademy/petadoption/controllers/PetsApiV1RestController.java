package com.launchacademy.petadoption.controllers;

import com.launchacademy.petadoption.models.AdoptablePet;
import com.launchacademy.petadoption.models.PetType;
import com.launchacademy.petadoption.services.AdoptablePetService;
import com.launchacademy.petadoption.services.PetTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pets")
public class PetsApiV1RestController {

  private final AdoptablePetService adoptablePetService;
  private final PetTypeService petTypeService;

  @Autowired
  public PetsApiV1RestController(AdoptablePetService adoptablePetService,
      PetTypeService petTypeService) {
    this.adoptablePetService = adoptablePetService;
    this.petTypeService = petTypeService;
  }

  @GetMapping
  public ResponseEntity getTypeList() {
    List<PetType> petTypes = petTypeService.getPetTypes();
    return new ResponseEntity<>(petTypes,
        petTypes.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
  }

  @GetMapping("/{type}")
  public ResponseEntity getByType(@PathVariable String type) {
    List<AdoptablePet> petsByType = petTypeService.getPetsByType(type);
    return new ResponseEntity<>(petsByType,
        petsByType.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
  }

  @GetMapping("/{type}/{id}")
  public ResponseEntity getById(@PathVariable String type, @PathVariable Long id) {
    AdoptablePet pet = adoptablePetService.getPetByTypeAndId(type, id);
    return new ResponseEntity<>(pet == null ? new AdoptablePet() : pet,
        pet == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
  }
}
