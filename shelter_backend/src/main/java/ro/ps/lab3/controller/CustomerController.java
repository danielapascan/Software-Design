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
import ro.ps.lab3.dto.AdoptionResponseDTO;
import ro.ps.lab3.dto.donation.DonationResponseDTO;
import ro.ps.lab3.dto.person.CustomerRequestDTO;
import ro.ps.lab3.dto.person.CustomerResponseDTO;
import ro.ps.lab3.exception.ExceptionBody;
import ro.ps.lab3.service.client.CustomerService;

import java.util.List;
import java.util.UUID;

/**
 * A REST controller for managing customers.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "Retrieve all customers, sorted and paginated")
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customers found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Customers not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<Page<CustomerResponseDTO>> getAll(
            @RequestParam(value = "sortBy", required = false, defaultValue = "email") String sortBy,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        return ResponseEntity.ok().body(customerService.getCustomers(sortBy, pageNumber, pageSize));
    }

    @Operation(summary = "Save a new customer")
    @PostMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer saved",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<CustomerResponseDTO> saveCustomer(
            @Valid @RequestBody CustomerRequestDTO customerRequestDTO
    ) {
        return new ResponseEntity<>(customerService.saveCustomer(customerRequestDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a customer by ID")
    @DeleteMapping("/all/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer deleted",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Customer not deleted",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<Boolean> deleteCustomer(
            @PathVariable("id") UUID id
    ) {
        return new ResponseEntity<>(customerService.deleteCustomer(id), HttpStatus.OK);
    }

    @Operation(summary = "Retrieve donations by customer ID")
    @GetMapping("/all/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Donations found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DonationResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Donations not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<List<DonationResponseDTO>> seeDonations(
            @PathVariable("id") UUID id
    ) {
        return new ResponseEntity<>(customerService.seeDonations(id), HttpStatus.OK);
    }

    @Operation(summary = "Retrieve adoptions by customer ID")
    @GetMapping("/all/adoptions/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Adoptions found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AdoptionResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Adoptions not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<List<AdoptionResponseDTO>> seeAdoptions(
            @PathVariable("id") UUID id
    ) {
        return new ResponseEntity<>(customerService.seeAdoptions(id), HttpStatus.OK);
    }

}
