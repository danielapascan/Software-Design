package ro.ps.lab3.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.ps.lab3.dto.AnimalNeedsRequestDTO;
import ro.ps.lab3.dto.AnimalNeedsResponseDTO;
import ro.ps.lab3.dto.AnimalResponseDTO;
import ro.ps.lab3.dto.CollectionResponseDTO;
import ro.ps.lab3.exception.ExceptionBody;
import ro.ps.lab3.service.animalNeeds.AnimalNeedsService;

import java.util.UUID;

/**
 * A REST controller for managing animal needs.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/needs")
@RequiredArgsConstructor
public class AnimalNeedsController {
    private final AnimalNeedsService animalNeedsService;

    @Operation(summary = "Retrieve all animal needs, sorted and paginated")
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'VOLUNTEER')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal Needs found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AnimalNeedsResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Animal Needs not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<?> getAll(
            @RequestParam(value = "sortBy", required = false, defaultValue = "food") String sortBy,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize
    ) {
        return new ResponseEntity<>(new CollectionResponseDTO<>(animalNeedsService.getAnimalNeeds(sortBy, pageNumber, pageSize), animalNeedsService.getAnimalNeeds(sortBy, pageNumber, pageSize).size()), HttpStatus.OK);
    }

    @Operation(summary = "Retrieve animal needs by ID")
    @GetMapping("all/{id}")
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'VOLUNTEER')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal's Needs found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AnimalNeedsResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Animal's Needs not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<AnimalNeedsResponseDTO> findById(
            @PathVariable("id") UUID id
    ) {
        return new ResponseEntity<>(
                animalNeedsService.findById(id),
                HttpStatus.OK
        );
    }

    @Operation(summary = "Save new animal needs")
    @PostMapping("/all/{id}")
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'VOLUNTEER','CUSTOMER')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal Needs saved",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AnimalNeedsResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<AnimalNeedsResponseDTO> saveNeeds(
            @PathVariable("id") UUID id,
            @Valid @RequestBody AnimalNeedsRequestDTO animalNeedsRequestDTO
    ) {
        return new ResponseEntity<>(animalNeedsService.saveAnimalNeeds(id, animalNeedsRequestDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Update animal needs by ID")
    @PutMapping("/all/{id}")
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'VOLUNTEER')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal Needs updated",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AnimalNeedsResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Animal Needs not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<AnimalNeedsResponseDTO> updateNeeds(
            @PathVariable("id") UUID id,
            @Valid @RequestBody AnimalNeedsRequestDTO animalNeedsRequestDTO
    ) {
        return new ResponseEntity<>(animalNeedsService.updateAnimalNeeds(id, animalNeedsRequestDTO), HttpStatus.OK);
    }

}
