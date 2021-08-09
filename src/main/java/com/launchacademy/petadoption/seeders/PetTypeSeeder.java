package com.launchacademy.petadoption.seeders;

import com.launchacademy.petadoption.models.PetType;
import com.launchacademy.petadoption.services.PetTypeService;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PetTypeSeeder {

  public static void seed(PetTypeService petTypeService) {
    if (!petTypeService.getPetTypes().isEmpty()) {
      return;
    }

    List<String> types = Arrays.asList("cat", "dog");
    List<String> imgUrls = Arrays.asList(
        "https://www.purina.co.uk/sites/default/files/2020-12/Understanding%20Your%20Cat%27s%20Body%20LanguageHERO.jpg",
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/dog-puppy-on-garden-royalty-free-image-1586966191.jpg");
    List<String> descriptions = Arrays.asList(
        "The cat is very cute an innocent animal. They have sharp claws, furry body and a bushy tail.",
        "A popular pet because they are usually playful, friendly, loyal and listen to humans.");

    for (int i = 0; i < types.size(); i++) {
      PetType type = new PetType();
      type.setType(types.get(i));
      type.setImgUrl(imgUrls.get(i));
      type.setDescription(descriptions.get(i));
      petTypeService.addPetType(type);
    }
  }
}
