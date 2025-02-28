package ro.ps.lab3.service.animalNeeds;

import ro.ps.lab3.dto.*;
import ro.ps.lab3.dto.AnimalNeedsResponseDTO;

import java.util.List;
import java.util.UUID;

/**
 * This interface defines operations related to managing animal needs.
 */
public interface AnimalNeedsService {
    /**
     * Retrieves a list of animal needs with optional sorting, pagination, and page size.
     *
     * @param sortBy     The field to sort by (e.g., "food", "love").
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The number of animal needs per page.
     * @return A list of {@link AnimalNeedsResponseDTO} objects.
     */
    List<AnimalNeedsResponseDTO> getAnimalNeeds(String sortBy, Integer pageNumber, Integer pageSize);

    /**
     * Saves animal needs for the specified animal.
     *
     * @param id                    The ID of the animal.
     * @param animalNeedsRequestDTO The DTO containing animal needs information to be saved.
     * @return The saved {@link AnimalNeedsResponseDTO} object.
     */
    AnimalNeedsResponseDTO saveAnimalNeeds(UUID id, AnimalNeedsRequestDTO animalNeedsRequestDTO);

    /**
     * Deletes animal needs with the specified ID.
     *
     * @param id The ID of the animal needs to delete.
     * @return {@code true} if the animal needs were successfully deleted, {@code false} otherwise.
     */
    boolean deleteAnimalNeeds(UUID id);

    /**
     * Updates animal needs with new information.
     *
     * @param id                    The ID of the animal needs to update.
     * @param animalNeedsRequestDTO The DTO containing updated animal needs information.
     * @return The updated {@link AnimalNeedsResponseDTO} object.
     */
    AnimalNeedsResponseDTO updateAnimalNeeds(UUID id, AnimalNeedsRequestDTO animalNeedsRequestDTO);

    /**
     * Caută și returnează nevoile unui animal pe baza identificatorului dat.
     *
     * @param id Identificatorul unic al animalului pentru a căuta nevoile sale.
     * @return DTO (Data Transfer Object) care conține nevoile animalului găsit.
     */
    AnimalNeedsResponseDTO findById(UUID id);
}
