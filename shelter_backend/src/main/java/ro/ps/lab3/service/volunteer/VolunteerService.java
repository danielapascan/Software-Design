package ro.ps.lab3.service.volunteer;

import org.springframework.data.domain.Page;
import ro.ps.lab3.dto.person.VolunteerRequestDTO;
import ro.ps.lab3.dto.person.VolunteerResponseDTO;
import ro.ps.lab3.exception.NotFoundException;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for managing volunteers.
 */
public interface VolunteerService {
    /**
     * Retrieves a list of volunteers.
     *
     * @param sortBy     the property to sort by
     * @param pageNumber the page number
     * @param pageSize   the size of each page
     * @return a list of {@link VolunteerResponseDTO}
     */
    Page<VolunteerResponseDTO> getVolunteers(String sortBy, Integer pageNumber, Integer pageSize);

    /**
     * Saves a new volunteer.
     *
     * @param volunteerRequestDTO the request DTO for the volunteer
     * @return the response DTO for the saved volunteer
     */
    VolunteerResponseDTO saveVolunteer(VolunteerRequestDTO volunteerRequestDTO);

    /**
     * Deletes a volunteer by ID.
     *
     * @param id the ID of the volunteer to delete
     * @return true if the volunteer was successfully deleted, otherwise false
     * @throws NotFoundException if the volunteer with the given ID is not found
     */
    boolean deleteVolunteer(UUID id);
}
