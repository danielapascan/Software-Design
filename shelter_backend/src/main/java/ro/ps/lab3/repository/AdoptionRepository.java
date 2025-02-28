package ro.ps.lab3.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.ps.lab3.model.AdoptionEntity;

import java.util.UUID;

/**
 * Repository interface for accessing AdoptionEntity objects from the database.
 */
@Repository
public interface AdoptionRepository extends JpaRepository<AdoptionEntity, UUID> {
    /**
     * Custom query to find all adoptions and return a sorted page of results.
     *
     * @param page The pageable object specifying pagination parameters.
     * @return A page of AdoptionEntity objects sorted by default.
     */
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM ADOPTION"
    )
    Page<AdoptionEntity> findAllSorted(Pageable page);
}
