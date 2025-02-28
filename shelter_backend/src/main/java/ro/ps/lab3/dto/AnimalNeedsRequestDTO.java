package ro.ps.lab3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Data transfer object (DTO) representing the needs of an animal as a request.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalNeedsRequestDTO {
    private int love;
    private int cleanCage;
    private int cleanAnimal;
    private int walk;
    private int food;
}
