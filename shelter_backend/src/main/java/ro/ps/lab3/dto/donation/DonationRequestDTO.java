package ro.ps.lab3.dto.donation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Data transfer object (DTO) representing a donation request.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonationRequestDTO {
    private double amount;
    private String cause;
    private LocalDate localDate;
    private String description;

}
