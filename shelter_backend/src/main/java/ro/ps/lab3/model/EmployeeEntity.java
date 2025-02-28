package ro.ps.lab3.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Represents an employee entity in the system, which is a type of person.
 */
@Table(name = "PERSON")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("EMPLOYEE")
public class EmployeeEntity extends PersonEntity {
    @Column(name = "EMPLOYMENT_DATE")
    private LocalDate employmentDate;
    @Column(name = "SALARY")
    @NotNull
    private Double salary;

}
