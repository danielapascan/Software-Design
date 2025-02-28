package ro.ps.lab3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.ps.lab3.dto.person.CustomerResponseDTO;

import java.time.LocalDate;
import java.util.UUID;
/**
 * Data transfer object (DTO) representing the response for an adoption.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdoptionResponseDTO {
    private UUID id;
    private LocalDate localDate;
    private AnimalResponseDTO animalEntity;
    private CustomerResponseDTO personEntity;
}
