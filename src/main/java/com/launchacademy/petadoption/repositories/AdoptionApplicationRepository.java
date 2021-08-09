package com.launchacademy.petadoption.repositories;

import com.launchacademy.petadoption.models.AdoptionApplication;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptionApplicationRepository extends CrudRepository<AdoptionApplication, Long> {

  List<AdoptionApplication> findAllByApplicationStatusEqualsIgnoreCase(String status);

}
