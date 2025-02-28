package ro.ps.lab3.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ro.ps.lab3.dto.CollectionResponseDTO;
import ro.ps.lab3.dto.person.PersonResponseDTO;
import ro.ps.lab3.service.person.PersonService;
import org.springframework.security.core.Authentication;

/**
 * Controller class for handling person-related HTTP requests.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @Operation(summary = "Retrieve all persons, filtered, sorted, and paginated")
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getAll(
            @RequestParam(value = "searchBy", required = false, defaultValue = "ADMIN") String searchBy,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize
    ) {
        return new ResponseEntity<>(new CollectionResponseDTO<>(personService.getPersons(searchBy, pageNumber, pageSize), personService.getPersons(searchBy, pageNumber, pageSize).size()), HttpStatus.OK);
    }

    @Operation(summary = "Retrieve information about the currently logged-in person")
    @GetMapping("/info")
    public ResponseEntity<PersonResponseDTO> getLoggedPersonInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) authentication.getPrincipal();

        return new ResponseEntity<>(
                personService.findByEmail(email),
                HttpStatus.OK
        );
    }

}
