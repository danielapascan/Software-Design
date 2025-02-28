package ro.ps.lab3.dto.donation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.ps.lab3.dto.person.PersonResponseDTO;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Data transfer object (DTO) representing the response for a donation.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonationResponseDTO {
    private UUID id;
    private double amount;
    private String cause;
    private LocalDate localDate;
    private String description;
    private PersonResponseDTO personEntity;
}
