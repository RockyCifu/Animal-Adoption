package com.launchacademy.petadoption.repositories;

import com.launchacademy.petadoption.models.AdoptablePet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptablePetRepository extends CrudRepository<AdoptablePet, Long> {

}
