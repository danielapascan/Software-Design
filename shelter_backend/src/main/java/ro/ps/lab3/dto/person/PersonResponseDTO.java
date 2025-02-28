package ro.ps.lab3.dto.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.ps.lab3.model.Role;

import java.util.UUID;
/**
 * Data transfer object (DTO) representing the response for a person.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponseDTO {
    private UUID id;
    private String email;
    private String password;
    private Role role;

}
