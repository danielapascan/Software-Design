package ro.ps.lab3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
/**
 * Represents a customer entity in the system.
 */
@Table(name = "PERSON")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("CUSTOMER")
public class CustomerEntity extends PersonEntity {
    @OneToMany(mappedBy = "personEntity",cascade = CascadeType.ALL)
    private List<AdoptionEntity> adoptionEntities;

    @OneToMany(mappedBy = "personEntity",cascade = CascadeType.ALL)
    private List<DonationEntity> donationEntities;
}
