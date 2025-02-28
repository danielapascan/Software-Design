package ro.ps.lab3.service.employee;

import org.springframework.data.domain.Page;
import ro.ps.lab3.dto.person.EmployeeRequestDTO;
import ro.ps.lab3.dto.person.EmployeeResponseDTO;
import ro.ps.lab3.exception.NotFoundException;

import java.util.List;
import java.util.UUID;

/**
 * This interface defines operations related to managing employees.
 */
public interface EmployeeService {
    /**
     * Retrieves a list of employees sorted by the specified criteria.
     *
     * @param sortBy     the field to sort by
     * @param pageNumber the page number
     * @param pageSize   the size of each page
     * @return a list of {@link EmployeeResponseDTO}
     */
    Page<EmployeeResponseDTO> getEmployees(String sortBy, Integer pageNumber, Integer pageSize);

    /**
     * Saves an employee.
     *
     * @param employeeRequestDTO the employee to be saved
     * @return the saved employee
     */
    EmployeeResponseDTO saveEmployee(EmployeeRequestDTO employeeRequestDTO);

    /**
     * Deletes an employee by ID.
     *
     * @param id the ID of the employee to delete
     * @return true if the employee was successfully deleted, otherwise false
     * @throws NotFoundException if the employee with the specified ID is not found
     */
    boolean deleteEmployee(UUID id);

    /**
     * Acordă o creștere salarială unui angajat pe baza identificatorului dat și a datelor primite.
     *
     * @param id                 Identificatorul unic al angajatului pentru a fi actualizat.
     * @param employeeRequestDTO DTO (Data Transfer Object) care conține informațiile actualizate despre angajat, inclusiv noua sa leafă.
     * @return DTO care conține informațiile actualizate ale angajatului, inclusiv leafa actualizată.
     * @throws NotFoundException dacă nu se găsește un angajat cu identificatorul dat.
     */
    EmployeeResponseDTO giveRaise(UUID id, EmployeeRequestDTO employeeRequestDTO);
}
