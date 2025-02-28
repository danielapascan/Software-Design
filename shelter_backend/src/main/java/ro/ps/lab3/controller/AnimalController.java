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
import ro.ps.lab3.dto.*;
import ro.ps.lab3.exception.ExceptionBody;
import ro.ps.lab3.service.animal.AnimalService;

import java.util.UUID;

/**
 * A REST controller for managing animals.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/animals")
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    @Operation(summary = "Retrieve all animals, sorted and paginated")
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'VOLUNTEER','CUSTOMER','ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animals found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AnimalResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Animals not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<Page<AnimalResponseDTO>> getAll(
            @RequestParam(value = "searchBy", required = false, defaultValue = "Dog") String searchBy,
            @RequestParam(value = "sortBy", required = false, defaultValue = "age") String sortBy,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "8") Integer pageSize
    ) {
        return ResponseEntity.ok().body(animalService.getAnimals(searchBy, sortBy, pageNumber, pageSize));
    }

    @Operation(summary = "Retrieve an animal by ID")
    @GetMapping("/all/{id}")
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'VOLUNTEER','CUSTOMER','ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AnimalResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Animal not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<AnimalResponseDTO> findById(
            @PathVariable("id") UUID id
    ) {
        return new ResponseEntity<>(
                animalService.findById(id),
                HttpStatus.OK
        );
    }

    @Operation(summary = "Save a new animal")
    @PostMapping("/all")
    @PreAuthorize("hasAnyRole('EMPLOYEE','VOLUNTEER','CUSTOMER')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal saved",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AnimalResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<AnimalResponseDTO> saveAnimal(
            @Valid @RequestBody AnimalRequestDTO animalRequestDTO
    ) {
        return new ResponseEntity<>(animalService.saveAnimal(animalRequestDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete an animal by ID")
    @DeleteMapping("/all/{id}")
    @PreAuthorize("hasAnyRole('EMPLOYEE','VOLUNTEER')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal deleted",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AnimalResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Animal not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<Boolean> deleteAnimal(
            @PathVariable("id") UUID id
    ) {
        return new ResponseEntity<>(animalService.deleteAnimal(id), HttpStatus.OK);
    }

    @Operation(summary = "Update an animal by ID")
    @PutMapping("/all/{id}")
    @PreAuthorize("hasAnyRole('EMPLOYEE','VOLUNTEER')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal updated",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AnimalResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Animal not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<AnimalResponseDTO> updateAnimal(
            @PathVariable("id") UUID id,
            @Valid @RequestBody AnimalRequestDTO animalRequestDTO
    ) {
        return new ResponseEntity<>(animalService.updateAnimal(id, animalRequestDTO), HttpStatus.OK);
    }
}
