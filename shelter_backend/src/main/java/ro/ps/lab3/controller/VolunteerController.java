package ro.ps.lab3.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.ps.lab3.dto.person.VolunteerRequestDTO;
import ro.ps.lab3.dto.person.VolunteerResponseDTO;
import ro.ps.lab3.exception.ExceptionBody;
import ro.ps.lab3.service.volunteer.VolunteerService;

import java.util.UUID;

/**
 * Controller class for handling volunteer-related HTTP requests.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/volunteers")
@RequiredArgsConstructor
public class VolunteerController {
    private final VolunteerService volunteerService;

    @Operation(summary = "Retrieve all volunteers, sorted and paginated")
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Volunteers found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VolunteerResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Volunteers not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<Page<VolunteerResponseDTO>> getAll(
            @RequestParam(value = "sortBy", required = false, defaultValue = "email") String sortBy,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        return ResponseEntity.ok().body(volunteerService.getVolunteers(sortBy, pageNumber, pageSize));
    }

    @Operation(summary = "Save a new volunteer")
    @PostMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Volunteer saved",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VolunteerResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<VolunteerResponseDTO> saveVolunteer(
            @Valid @RequestBody VolunteerRequestDTO volunteerRequestDTO
    ) {
        return new ResponseEntity<>(volunteerService.saveVolunteer(volunteerRequestDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a volunteer by ID")
    @DeleteMapping("/all/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Volunteer deleted",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = VolunteerResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Volunteer not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<Boolean> deleteVolunteer(
            @PathVariable("id") UUID id
    ) {
        return new ResponseEntity<>(volunteerService.deleteVolunteer(id), HttpStatus.OK);
    }


}
