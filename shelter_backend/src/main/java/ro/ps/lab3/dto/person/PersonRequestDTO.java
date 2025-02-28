package ro.ps.lab3.dto.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.ps.lab3.model.Role;
/**
 * Data transfer object (DTO) representing the request for a person.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequestDTO {
    private String email;
    private String password;
    private Role role;
}
