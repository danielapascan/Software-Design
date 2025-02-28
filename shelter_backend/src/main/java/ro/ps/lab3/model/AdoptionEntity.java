package ro.ps.lab3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;
/**
 * Represents an adoption in the system.
 */
@Table(name = "ADOPTION")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AdoptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "DATE")
    private LocalDate localDate;
    @OneToOne(mappedBy = "adoptionEntity")
    private AnimalEntity animalEntity;
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity personEntity;
}
