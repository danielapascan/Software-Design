package ro.ps.lab3.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.ps.lab3.model.DonationEntity;

import java.util.UUID;

/**
 * Repository interface for accessing DonationEntity objects from the database.
 */
@Repository
public interface DonationRepository extends JpaRepository<DonationEntity, UUID> {
    /**
     * Custom query to find all donations and return a sorted page of results.
     *
     * @param page The pageable object specifying pagination parameters.
     * @return A page of DonationEntity objects sorted according to the provided page.
     */
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM DONATION"
    )
    Page<DonationEntity> findAllSorted(Pageable page);
}
