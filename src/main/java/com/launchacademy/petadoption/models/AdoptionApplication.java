package com.launchacademy.petadoption.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "adoption_applications")
public class AdoptionApplication {

  @Id
  @SequenceGenerator(name = "adoption_application_generator", sequenceName = "adoption_applications_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adoption_application_generator")
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
  @Column(name = "home_status", nullable = false)
  private String homeStatus;

  @NotBlank
  @Column(name = "application_status", nullable = false)
  private String applicationStatus = "pending";

  @ManyToOne
  @JoinColumn(name = "adoptable_pet_id")
  @JsonIgnoreProperties("applications")
  private AdoptablePet adoptablePet;
}
