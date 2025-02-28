package ro.ps.lab3.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ro.ps.lab3.dto.person.PersonRequestDTO;
import ro.ps.lab3.dto.person.PersonResponseDTO;
import ro.ps.lab3.model.PersonEntity;

import java.util.List;

/**
 * Mapper interface for converting between PersonEntity and PersonDTO objects.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PersonMapper {
    /**
     * Converts a PersonEntity object to a PersonResponseDTO object.
     *
     * @param personEntity The PersonEntity object to convert.
     * @return The converted PersonResponseDTO object.
     */
    PersonResponseDTO userEntityToUserResponseDTO(PersonEntity personEntity);

    /**
     * Converts a PersonRequestDTO object to a PersonEntity object.
     *
     * @param personRequestDTO The PersonRequestDTO object to convert.
     * @return The converted PersonEntity object.
     */
    PersonEntity userRequestDTOToUserEntity(PersonRequestDTO personRequestDTO);

    /**
     * Converts a list of PersonEntity objects to a list of PersonResponseDTO objects.
     *
     * @param userEntities The list of PersonEntity objects to convert.
     * @return The converted list of PersonResponseDTO objects.
     */
    List<PersonResponseDTO> userEntityListToUserResponseDTOList(List<PersonEntity> userEntities);
}
