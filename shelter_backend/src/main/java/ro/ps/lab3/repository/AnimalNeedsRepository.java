package ro.ps.lab3.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.ps.lab3.model.AnimalNeedsEntity;

import java.util.UUID;

/**
 * Repository interface for accessing AnimalNeedsEntity objects from the database.
 */
@Repository
public interface AnimalNeedsRepository extends JpaRepository<AnimalNeedsEntity, UUID> {
    /**
     * Custom query to find all animal needs and return a sorted page of results.
     *
     * @param page The pageable object specifying pagination parameters.
     * @return A page of AnimalNeedsEntity objects sorted by default.
     */
    @Query(
            nativeQuery = true,
            value = "SELECT an.* FROM ANIMAL_NEEDS an"
    )
    Page<AnimalNeedsEntity> findAllSorted(Pageable page);

}
