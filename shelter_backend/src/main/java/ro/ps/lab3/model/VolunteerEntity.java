package ro.ps.lab3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
/**
 * Entity class representing a volunteer in the system, extending PersonEntity.
 */
@Table(name = "PERSON")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("VOLUNTEER")
public class VolunteerEntity extends PersonEntity {
    @Column(name = "VOLUNTEERING_STARTING_DATE")
    private LocalDate volunteeringStartingDate;
}
