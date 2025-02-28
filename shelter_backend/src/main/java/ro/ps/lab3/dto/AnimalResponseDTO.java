package ro.ps.lab3.dto;

import lombok.*;

import java.util.UUID;
/**
 * Data transfer object (DTO) representing a response for an animal entity.
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponseDTO {
    private UUID id;
    private String name;
    private int age;
    private String breed;
    private String gender;
    private String species;
    private AnimalNeedsResponseDTO animalNeedsEntity;
}
