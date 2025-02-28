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
import ro.ps.lab3.service.adoption.AdoptionService;

import java.util.UUID;

/**
 * A REST controller for managing adoptions.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/adoptions")
@RequiredArgsConstructor
public class AdoptionController {
    private final AdoptionService adoptionService;

    @Operation(summary = "Retrieve all adoptions, sorted and paginated")
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Adoptions found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AdoptionResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Adoptions not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<Page<AdoptionResponseDTO>> getAll(
            @RequestParam(value = "sortBy", required = false, defaultValue = "date") String sortBy,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        return ResponseEntity.ok().body(adoptionService.getAdoptions(sortBy, pageNumber, pageSize));
    }

    @Operation(summary = "Save a new adoption")
    @PostMapping("/all/{idUser}/{idAnimal}")
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Adoption saved",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AdoptionResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<AdoptionResponseDTO> saveAdoption(
            @PathVariable("idUser") UUID idUser,
            @PathVariable("idAnimal") UUID idAnimal,
            @Valid @RequestBody AdoptionRequestDTO adoptionRequestDTO) {
        return new ResponseEntity<>(adoptionService.saveAdoption(idUser, idAnimal, adoptionRequestDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete an adoption by ID")
    @DeleteMapping("/all/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Adoption deleted",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AdoptionResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Adoption not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<Boolean> deleteAdoption(
            @PathVariable("id") UUID id
    ) {
        return new ResponseEntity<>(adoptionService.deleteAdoption(id), HttpStatus.OK);
    }

    @Operation(summary = "Update an adoption by ID")
    @PutMapping("/all/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Adoption updated",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AdoptionResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Adoption not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<AdoptionResponseDTO> updateAdoption(
            @PathVariable("id") UUID id,
            @Valid @RequestBody AdoptionRequestDTO adoptionRequestDTO
    ) {
        return new ResponseEntity<>(adoptionService.updateAdoption(id, adoptionRequestDTO), HttpStatus.OK);
    }
}
