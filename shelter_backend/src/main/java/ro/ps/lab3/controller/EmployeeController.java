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
import ro.ps.lab3.dto.person.EmployeeRequestDTO;
import ro.ps.lab3.dto.person.EmployeeResponseDTO;
import ro.ps.lab3.exception.ExceptionBody;
import ro.ps.lab3.service.employee.EmployeeService;

import java.util.UUID;

/**
 * Controller class for handling employee-related HTTP requests.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @Operation(summary = "Retrieve all employees, sorted and paginated")
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employees found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Employees not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<Page<EmployeeResponseDTO>> getAll(
            @RequestParam(value = "sortBy", required = false, defaultValue = "salary") String sortBy,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        return ResponseEntity.ok().body(employeeService.getEmployees(sortBy, pageNumber, pageSize));
    }

    @Operation(summary = "Save a new employee")
    @PostMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employees saved",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeResponseDTO.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<EmployeeResponseDTO> saveEmployee(
            @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO
    ) {
        return new ResponseEntity<>(employeeService.saveEmployee(employeeRequestDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete an employee by ID")
    @DeleteMapping("/all/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee deleted",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Employee not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<Boolean> deleteEmployee(
            @PathVariable("id") UUID id
    ) {
        return new ResponseEntity<>(employeeService.deleteEmployee(id), HttpStatus.OK);
    }

    @Operation(summary = "Give a raise to an employee by ID")
    @PutMapping("/all/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeResponseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Employees not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))}),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionBody.class))})
    })
    public ResponseEntity<EmployeeResponseDTO> giveRaise(
            @PathVariable("id") UUID id,
            @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO
    ) {
        return new ResponseEntity<>(employeeService.giveRaise(id, employeeRequestDTO), HttpStatus.OK);
    }
}
