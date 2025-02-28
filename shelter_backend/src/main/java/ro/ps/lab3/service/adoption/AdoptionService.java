package ro.ps.lab3.service.adoption;

import org.springframework.data.domain.Page;
import ro.ps.lab3.dto.*;

import java.util.List;
import java.util.UUID;

/**
 * This interface defines operations related to managing adoptions.
 */
public interface AdoptionService {

    /**
     * Retrieves a list of adoptions with optional sorting, pagination, and page size.
     *
     * @param sortBy     The field to sort by (e.g., "date", "status").
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The number of adoptions per page.
     * @return A list of {@link ro.ps.lab3.dto.AdoptionResponseDTO} objects.
     */
    Page<AdoptionResponseDTO> getAdoptions(String sortBy, Integer pageNumber, Integer pageSize);

    /**
     * Saves a new adoption.
     *
     * @param idUser             The ID of the adopting person.
     * @param idAnimal           The ID of the adopted animal.
     * @param adoptionRequestDTO The DTO containing adoption information to be saved.
     * @return The saved {@link ro.ps.lab3.dto.AdoptionResponseDTO} object.
     */
    AdoptionResponseDTO saveAdoption(UUID idUser, UUID idAnimal, AdoptionRequestDTO adoptionRequestDTO);

    /**
     * Deletes an adoption with the specified ID.
     *
     * @param id The ID of the adoption to delete.
     * @return {@code true} if the adoption was successfully deleted, {@code false} otherwise.
     */
    boolean deleteAdoption(UUID id);

    /**
     * Updates an adoption with new information.
     *
     * @param id                 The ID of the adoption to update.
     * @param adoptionRequestDTO The DTO containing updated adoption information.
     * @return The updated {@link ro.ps.lab3.dto.AdoptionResponseDTO} object.
     */
    AdoptionResponseDTO updateAdoption(UUID id, AdoptionRequestDTO adoptionRequestDTO);
}
