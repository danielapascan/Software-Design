package ro.ps.lab3.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.ps.lab3.model.PersonEntity;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for accessing PersonEntity objects from the database.
 */
@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, UUID> {
    Optional<PersonEntity> findByEmail(String email);

    /**
     * Custom query to find all persons filtered by a role and return a sorted page of results.
     *
     * @param page     The pageable object specifying pagination parameters.
     * @param searchBy The role to search for.
     * @return A page of PersonEntity objects filtered by role and sorted according to the provided page.
     */
    @Query(
            nativeQuery = true,
            value = "SELECT p.* FROM PERSON p WHERE p.role LIKE :searchBy"
    )
    Page<PersonEntity> findAll(Pageable page, @Param("searchBy") String searchBy);

}
