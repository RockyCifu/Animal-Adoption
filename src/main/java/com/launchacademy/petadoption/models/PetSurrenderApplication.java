package com.launchacademy.petadoption.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "pet_surrender_applications")
public class PetSurrenderApplication {

  @Id
  @SequenceGenerator(name = "pet_surrender_application_generator", sequenceName = "pet_surrender_applications_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_surrender_application_generator")
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @NotBlank
  @Column(name = "name", nullable = false)
  private String name;

  @NotBlank
  @Length(min = 7, max = 20)
  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;

  @Email
  @NotBlank
  @Column(name = "email", nullable = false)
  private String email;

  @NotBlank
  @Column(name = "status", nullable = false)
  private String status = "pending";

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @ToString.Exclude
  @JsonIgnoreProperties("petSurrenderApplication")
  private AdoptablePet adoptablePet;
}
