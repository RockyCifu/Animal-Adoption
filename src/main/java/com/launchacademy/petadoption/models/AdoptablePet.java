package com.launchacademy.petadoption.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Table;
import org.hibernate.validator.constraints.URL;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "adoptable_pets")
public class AdoptablePet {

  @Id
  @SequenceGenerator(name = "adoptable_pet_generator", sequenceName = "adoptable_pets_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adoptable_pet_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @NotBlank
  @Column(name = "name", nullable = false)
  private String name;

  @NotBlank
  @URL
  @Column(name = "img_url", nullable = false)
  private String imgUrl;

  @Positive
  @Column(name = "age")
  private Integer age;

  @Column(name = "vaccination_status")
  private Boolean vaccinationStatus = false;

  @NotBlank
  @Column(name = "adoption_story", nullable = false)
  private String adoptionStory;

  @NotNull
  @Column(name = "available_for_adoption", nullable = false)
  private Boolean availableForAdoption = true;

  @ManyToOne
  @JoinColumn(name = "pet_type_id", nullable = false)
  @JsonIgnoreProperties("adoptablePets")
  private PetType petType;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "adoptablePet")
  @ToString.Exclude
  @JsonIgnoreProperties("adoptablePet")
  private List<AdoptionApplication> applications = new ArrayList<>();

  @OneToOne(fetch = FetchType.EAGER, mappedBy = "adoptablePet", cascade = CascadeType.ALL)
  @ToString.Exclude
  @JsonIgnoreProperties("adoptablePet")
  private PetSurrenderApplication petSurrenderApplication;
}
