package ro.ps.lab3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
/**
 * Data transfer object (DTO) representing the needs of an animal.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalNeedsResponseDTO {
    private UUID id;
    private int love;
    private int cleanCage;
    private int cleanAnimal;
    private int walk;
    private int food;
}
