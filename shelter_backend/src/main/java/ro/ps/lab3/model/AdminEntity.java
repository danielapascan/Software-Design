package ro.ps.lab3.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
/**
 * Represents an admin in the system.
 */
@Table(name = "PERSON")
@Entity
@AllArgsConstructor
@Getter
@Setter
@DiscriminatorValue("ADMIN")
public class AdminEntity extends PersonEntity {

}
