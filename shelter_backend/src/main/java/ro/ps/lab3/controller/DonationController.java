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
import ro.ps.lab3.dto.donation.DonationRequestDTO;
import ro.ps.lab3.dto.donation.DonationResponseDTO;
import ro.ps.lab3.exception.ExceptionBody;
import ro.ps.lab3.service.donation.DonationService;

import java.util.UUID;

/**
 * A REST controller for managing donations.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/donations")
@RequiredArgsConstructor
public class DonationController {
    private final DonationService donationService;

    @Operation(summary = "Retrieve all donations, sorted and paginated")
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Donations found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DonationResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Donations not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<Page<DonationResponseDTO>> getAll(
            @RequestParam(value = "sortBy", required = false, defaultValue = "amount") String sortBy,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        return ResponseEntity.ok().body(donationService.getDonations(sortBy, pageNumber, pageSize));
    }

    @Operation(summary = "Save a new donation")
    @PostMapping("/all/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Donation saved",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DonationResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<DonationResponseDTO> saveDonation(
            @PathVariable("id") UUID id,
            @Valid @RequestBody DonationRequestDTO donationRequestDTO
    ) {
        return new ResponseEntity<>(donationService.saveDonation(id, donationRequestDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a donation by ID")
    @DeleteMapping("/all/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Donation deleted",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DonationResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Donation not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<Boolean> deleteDonation(
            @PathVariable("id") UUID id
    ) {
        return new ResponseEntity<>(donationService.deleteDonation(id), HttpStatus.OK);
    }

    @Operation(summary = "Update a donation by ID")
    @PutMapping("/all/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Donation updated",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DonationResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Donation not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<DonationResponseDTO> updateDonation(
            @PathVariable("id") UUID id,
            @Valid @RequestBody DonationRequestDTO donationRequestDTO
    ) {
        return new ResponseEntity<>(donationService.updateDonation(id, donationRequestDTO), HttpStatus.OK);
    }
}
