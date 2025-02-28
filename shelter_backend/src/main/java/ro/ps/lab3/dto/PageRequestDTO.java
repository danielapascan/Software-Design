package ro.ps.lab3.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.ps.lab3.validator.OddPageSize;

/**
 * Data transfer object (DTO) representing a page request.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @NotNull(message = "Page number is requested")
    private Integer pageNumber;
    @OddPageSize
    private Integer pageSize = 20;
}
