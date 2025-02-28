package ro.ps.lab3.dto;

import lombok.*;

/**
 * Data transfer object (DTO) representing a request to create or update an animal entity.
 */
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AnimalRequestDTO {
    private String name;
    private int age;
    private String breed;
    private String gender;
    private String species;
}
