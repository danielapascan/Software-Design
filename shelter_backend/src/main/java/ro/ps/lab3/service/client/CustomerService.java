package ro.ps.lab3.service.client;

import org.springframework.data.domain.Page;
import ro.ps.lab3.dto.AdoptionResponseDTO;
import ro.ps.lab3.dto.donation.DonationResponseDTO;
import ro.ps.lab3.dto.person.CustomerRequestDTO;
import ro.ps.lab3.dto.person.CustomerResponseDTO;

import java.util.List;
import java.util.UUID;

/**
 * This interface defines operations related to managing customers.
 */
public interface CustomerService {
    /**
     * Retrieves a list of customers with optional sorting, pagination, and page size.
     *
     * @param sortBy     The field to sort by (e.g., "firstName", "email").
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The number of customers per page.
     * @return A list of {@link CustomerResponseDTO} objects.
     */
    Page<CustomerResponseDTO> getCustomers(String sortBy, Integer pageNumber, Integer pageSize);

    /**
     * Saves a new customer.
     *
     * @param customerRequestDTO The DTO containing customer information to be saved.
     * @return The saved {@link CustomerResponseDTO} object.
     */
    CustomerResponseDTO saveCustomer(CustomerRequestDTO customerRequestDTO);

    /**
     * Deletes a customer with the specified ID.
     *
     * @param id The ID of the customer to delete.
     * @return {@code true} if the customer was successfully deleted, {@code false} otherwise.
     */
    boolean deleteCustomer(UUID id);

    /**
     * Returnează o listă de donații asociate unui client pe baza identificatorului dat.
     *
     * @param id Identificatorul unic al clientului pentru a căuta donațiile asociate.
     * @return Lista de DTO-uri (Data Transfer Objects) care conțin informațiile despre donațiile găsite.
     */
    List<DonationResponseDTO> seeDonations(UUID id);

    /**
     * Returnează o listă de adopții asociate unui client pe baza identificatorului dat.
     *
     * @param id Identificatorul unic al clientului pentru a căuta adopțiile asociate.
     * @return Lista de DTO-uri (Data Transfer Objects) care conțin informațiile despre adopțiile găsite.
     */
    List<AdoptionResponseDTO> seeAdoptions(UUID id);
}
