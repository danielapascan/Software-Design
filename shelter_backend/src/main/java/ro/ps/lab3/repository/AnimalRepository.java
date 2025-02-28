package ro.ps.lab3.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.ps.lab3.model.AnimalEntity;

import java.util.UUID;

/**
 * Repository interface for accessing AnimalEntity objects from the database.
 */
@Repository
public interface AnimalRepository extends JpaRepository<AnimalEntity, UUID> {
    AnimalEntity findByName(String name);

    /**
     * Custom query to find all animals matching a search criteria and return a sorted page of results.
     *
     * @param page     The pageable object specifying pagination parameters.
     * @param searchBy The search criteria to filter animals by species.
     * @return A page of AnimalEntity objects sorted by age and filtered by species.
     */
    @Query(
            nativeQuery = true,
            value = "SELECT a.* FROM ANIMAL a WHERE a.species LIKE :searchBy "
    )
    Page<AnimalEntity> findAllSortedByAge(Pageable page, @Param("searchBy") String searchBy);
}
