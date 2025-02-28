package ro.ps.lab3.dto.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
/**
 * Data transfer object (DTO) representing the request for creating or updating a volunteer.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerRequestDTO extends PersonRequestDTO {
    private LocalDate volunteeringStartingDate;
}
