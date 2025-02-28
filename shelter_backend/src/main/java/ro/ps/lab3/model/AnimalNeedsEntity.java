package ro.ps.lab3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
/**
 * Represents the needs of an animal in the system.
 */
@Table(name = "ANIMAL_NEEDS")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalNeedsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID")
    private UUID id;
    @Column(name = "LOVE")
    @NotNull
    @Min(value = 0, message = "Love must be greater than or equal to 0")
    @Max(value = 10, message = "Love must be less than or equal to 10")
    private int love;
    @Column(name = "CLEAN_CAGE")
    @NotNull
    @Min(value = 0, message = "CleanCage must be greater than or equal to 0")
    @Max(value = 10, message = "CleanCage must be less than or equal to 10")
    private int cleanCage;
    @Column(name = "CLEAN_ANIMAL")
    @NotNull
    @Min(value = 0, message = "CleanAnimal must be greater than or equal to 0")
    @Max(value = 10, message = "CleanAnimal must be less than or equal to 10")
    private int cleanAnimal;
    @Column(name = "WALK")
    @NotNull
    @Min(value = 0, message = "Walk must be greater than or equal to 0")
    @Max(value = 10, message = "Walk must be less than or equal to 10")
    private int walk;
    @Column(name = "FOOD")
    @NotNull
    @Min(value = 0, message = "Food must be greater than or equal to 0")
    @Max(value = 10, message = "Food must be less than or equal to 10")
    private int food;
    @OneToOne(mappedBy = "animalNeedsEntity")
    private AnimalEntity animalEntity;
}
