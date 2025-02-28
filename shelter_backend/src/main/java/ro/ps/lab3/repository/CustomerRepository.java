package ro.ps.lab3.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.ps.lab3.model.CustomerEntity;

import java.util.UUID;

/**
 * Repository interface for accessing CustomerEntity objects from the database.
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {
    /**
     * Custom query to find all customers and return a sorted page of results.
     *
     * @param page The pageable object specifying pagination parameters.
     * @return A page of CustomerEntity objects sorted according to the provided page.
     */
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM PERSON WHERE role='CUSTOMER'"
    )
    Page<CustomerEntity> findAllSorted(Pageable page);
}
