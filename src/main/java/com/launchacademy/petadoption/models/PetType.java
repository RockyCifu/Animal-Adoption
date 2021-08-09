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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
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
@Table(name = "pet_types")
public class PetType {

  @Id
  @SequenceGenerator(name = "pet_type_generator", sequenceName = "pet_types_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_type_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @NotBlank
  @Column(name = "type", nullable = false, unique = true)
  private String type;

  @URL
  @NotBlank
  @Column(name = "img_url", nullable = false)
  private String imgUrl;

  @Column(name = "description")
  private String description;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "petType", cascade = CascadeType.ALL)
  @ToString.Exclude
  @JsonIgnoreProperties("petType")
  private List<AdoptablePet> adoptablePets = new ArrayList<>();

}
