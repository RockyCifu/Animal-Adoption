package com.launchacademy.petadoption.repositories;

import com.launchacademy.petadoption.models.PetType;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetTypeRepository extends CrudRepository<PetType, Long> {

  List<PetType> findAllByTypeIgnoreCase(String type);

  PetType findByTypeIgnoreCase(String type);

  PetType findByType(String type);
}
