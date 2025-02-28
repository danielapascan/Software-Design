package ro.ps.lab3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;
/**
 * Represents a donation entity in the system.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DONATION")
public class DonationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private PersonEntity personEntity;
    @Column(name = "AMOUNT")
    @NotNull
    private double amount;
    @Column(name = "CAUSE")
    @NotBlank(message = "Cause is mandatory")
    private String cause;
    @Column(name = "DATE")
    private LocalDate localDate;
    @Column(name = "DESCRIPTION")
    @NotBlank(message = "Description is mandatory")
    private String description;

}
