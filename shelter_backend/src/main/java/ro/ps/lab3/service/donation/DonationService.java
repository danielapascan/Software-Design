package ro.ps.lab3.service.donation;

import org.springframework.data.domain.Page;
import ro.ps.lab3.dto.donation.DonationRequestDTO;
import ro.ps.lab3.dto.donation.DonationResponseDTO;

import java.util.List;
import java.util.UUID;

/**
 * This interface defines operations related to managing donations.
 */
public interface DonationService {
    /**
     * Retrieves a list of donations with optional sorting, pagination, and page size.
     *
     * @param sortBy     The field to sort by (e.g., "amount", "localDate").
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The number of donations per page.
     * @return A list of {@link DonationResponseDTO} objects.
     */
    Page<DonationResponseDTO> getDonations(String sortBy, Integer pageNumber, Integer pageSize);

    /**
     * Saves a new donation for the specified person.
     *
     * @param id                 The ID of the person making the donation.
     * @param donationRequestDTO The DTO containing donation information to be saved.
     * @return The saved {@link DonationResponseDTO} object.
     */
    DonationResponseDTO saveDonation(UUID id, DonationRequestDTO donationRequestDTO);

    /**
     * Deletes a donation with the specified ID.
     *
     * @param id The ID of the donation to delete.
     * @return {@code true} if the donation was successfully deleted, {@code false} otherwise.
     */
    boolean deleteDonation(UUID id);

    /**
     * Updates an existing donation with new information.
     *
     * @param id                 The ID of the donation to update.
     * @param donationRequestDTO The DTO containing updated donation information.
     * @return The updated {@link DonationResponseDTO} object.
     */
    DonationResponseDTO updateDonation(UUID id, DonationRequestDTO donationRequestDTO);
}
