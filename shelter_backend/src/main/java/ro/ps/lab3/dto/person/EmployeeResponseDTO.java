package ro.ps.lab3.dto.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
/**
 * Data transfer object (DTO) representing the response for an employee.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDTO extends PersonResponseDTO {
    private LocalDate employmentDate;
    private Double salary;
}
