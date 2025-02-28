package ro.ps.lab3.service.person;


import ro.ps.lab3.dto.person.PersonResponseDTO;

import java.util.List;

/**
 * Service interface for managing persons.
 */
public interface PersonService {

    /**
     * Retrieves a list of persons.
     *
     * @param searchBy   the search query
     * @param pageNumber the page number
     * @param pageSize   the size of each page
     * @return a list of {@link PersonResponseDTO}
     */
    List<PersonResponseDTO> getPersons(String searchBy, Integer pageNumber, Integer pageSize);

    /**
     * Caută și returnează o persoană pe baza adresei de email dată.
     *
     * @param email Adresa de email a persoanei pe care se dorește căutarea.
     * @return DTO (Data Transfer Object) care conține informațiile despre persoana găsită.
     */
    PersonResponseDTO findByEmail(String email);
}
