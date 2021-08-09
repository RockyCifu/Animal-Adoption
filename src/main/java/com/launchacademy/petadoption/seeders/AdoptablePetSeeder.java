package com.launchacademy.petadoption.seeders;

import com.launchacademy.petadoption.models.AdoptablePet;
import com.launchacademy.petadoption.services.AdoptablePetService;
import com.launchacademy.petadoption.services.PetTypeService;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class AdoptablePetSeeder {

  public static void seed(PetTypeService petTypeService, AdoptablePetService adoptablePetService) {
    if (!adoptablePetService.getPets().isEmpty()) {
      return;
    }

    List<String> types = Arrays.asList("cat", "cat", "dog", "dog");
    List<String> names = Arrays.asList("Ziggy", "Baloo", "Ace", "Christa");
    List<String> images = Arrays
        .asList("https://g.petango.com/photos/2213/31449f9a-b386-41a6-8ff1-5c155e1b66c9.jpg",
            "https://humanepro.org/sites/default/files/styles/article/public/images/post/cat-portrait.jpg?itok=YzekXgKK",
            "https://static.wixstatic.com/media/1b5818_27a7ad82a1464f82a573ee2cb80d5728~mv2.jpg/v1/fill/w_640,h_520,al_c,q_80,usm_0.66_1.00_0.01/1b5818_27a7ad82a1464f82a573ee2cb80d5728~mv2.webp",
            "https://specials-images.forbesimg.com/imageserve/5db4c7b464b49a0007e9dfac/960x0.jpg?fit=scale");
    List<Integer> ages = Arrays.asList(7, 6, 2, 3);
    List<String> adoptionStories = Arrays.asList(
        "Ziggy is a sweet and quiet 7-year-old tabby looking for her new home. Ziggy is shy and likes her environments to be quiet, predictable and routine. One she settles in she's very loving and affectionate. She likes to chase toys too! We don't know if Ziggy's ever met other cats or dogs, so any introductions to resident pets should be done very slowly. Because she can be sensitive to handling and noises, she is looking for a cat experienced, mellow home with just teens and adults. If you're ready to welcome Ziggy into your home, send in an adoption application today!",
        "Baloo was found wandering and was brought to PAWS so he could find a safe, indoor home with people to love him! He is a low key, easygoing cat who enjoys attention on his terms. He is very smart and knows what he likes.",
        "Ace is a sweet and sensitive boy who can be easily be overwhelmed by loud noises and fast movements. Once he feels comfortable, Ace is affectionate and enjoys gentle pets and attention. He also likes the company of other little dogs.",
        "Christa is incredibly active, social, outgoing, bouncy and exuberant. She can sometimes be overly excited with people when she first meets them, but Christa is working on her greeting style. She is super playful with other dogs..and loves to wrestle!");

    for (int i = 0; i < types.size(); i++) {
      AdoptablePet pet = new AdoptablePet();
      pet.setName(names.get(i));
      pet.setImgUrl(images.get(i));
      pet.setAge(ages.get(i));
      pet.setAdoptionStory(adoptionStories.get(i));
      pet.setPetType(petTypeService.getOneByType(types.get(i)));
      pet.setAvailableForAdoption(true);
      adoptablePetService.addAdoptablePet(pet);
    }
  }
}
