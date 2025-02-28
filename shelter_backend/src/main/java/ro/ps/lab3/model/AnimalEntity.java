package ro.ps.lab3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;
/**
 * Represents an animal in the system.
 */
@Table(name = "ANIMAL")
@Entity
@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AnimalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private UUID id;

    @Column(name = "NAME")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(name = "AGE")
    @NotNull
    private int age;

    @Column(name = "BREED")
    @NotBlank(message = "Breed is mandatory")
    private String breed;

    @Column(name = "GENDER")
    @NotBlank(message = "Gender is mandatory")
    private String gender;

    @Column(name = "SPECIES")
    @NotBlank(message = "Species is mandatory")
    private String species;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "animal_needs_id", referencedColumnName = "id")
    private AnimalNeedsEntity animalNeedsEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADOPTION_ID", referencedColumnName = "id")
    private AdoptionEntity adoptionEntity;
}
