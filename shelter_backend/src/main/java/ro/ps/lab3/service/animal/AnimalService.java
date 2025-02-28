package ro.ps.lab3.service.animal;

import org.springframework.data.domain.Page;
import ro.ps.lab3.dto.*;

import java.util.List;
import java.util.UUID;

/**
 * This interface defines operations related to managing animals.
 */
public interface AnimalService {
    /**
     * Retrieves a list of animals with optional search, sorting, pagination, and page size.
     *
     * @param searchBy   The field to search by (e.g., "species", "name").
     * @param sortBy     The field to sort by (e.g., "age", "name").
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The number of animals per page.
     * @return A list of {@link AnimalResponseDTO} objects.
     */
    Page<AnimalResponseDTO> getAnimals(String searchBy, String sortBy, Integer pageNumber, Integer pageSize);

    /**
     * Saves a new animal.
     *
     * @param animalRequestDTO The DTO containing animal information to be saved.
     * @return The saved {@link AnimalResponseDTO} object.
     */
    AnimalResponseDTO saveAnimal(AnimalRequestDTO animalRequestDTO);

    /**
     * Deletes an animal with the specified ID.
     *
     * @param id The ID of the animal to delete.
     * @return {@code true} if the animal was successfully deleted, {@code false} otherwise.
     */
    boolean deleteAnimal(UUID id);

    /**
     * Updates an existing animal with new information.
     *
     * @param id               The ID of the animal to update.
     * @param animalRequestDTO The DTO containing updated animal information.
     * @return The updated {@link AnimalResponseDTO} object.
     */
    AnimalResponseDTO updateAnimal(UUID id, AnimalRequestDTO animalRequestDTO);

    /**
     * Finds an animal by its name.
     *
     * @param name The name of the animal to find.
     * @return The {@link AnimalResponseDTO} object representing the found animal.
     */
    AnimalResponseDTO findByName(String name);

    /**
     * Caută și returnează un animal pe baza identificatorului dat.
     *
     * @param id Identificatorul unic al animalului pentru a căuta.
     * @return DTO (Data Transfer Object) care conține informațiile animalului găsit.
     */
    AnimalResponseDTO findById(UUID id);
}
