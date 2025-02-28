package ro.ps.lab3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
/**
 * Data transfer object (DTO) representing the request for an adoption.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdoptionRequestDTO {
    private LocalDate localDate;
}
