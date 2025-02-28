package ro.ps.lab3.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ro.ps.lab3.dto.AnimalRequestDTO;
import ro.ps.lab3.dto.AnimalResponseDTO;
import ro.ps.lab3.model.AnimalEntity;

import java.util.List;

/**
 * Mapper interface for converting between AnimalEntity and AnimalDTO objects.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnimalMapper {
    /**
     * Converts an AnimalEntity object to an AnimalResponseDTO object.
     *
     * @param animalEntity The AnimalEntity object to convert.
     * @return The converted AnimalResponseDTO object.
     */
    AnimalResponseDTO animalEntityToAnimalResponseDTO(AnimalEntity animalEntity);

    /**
     * Converts an AnimalRequestDTO object to an AnimalEntity object.
     *
     * @param animalRequestDTO The AnimalRequestDTO object to convert.
     * @return The converted AnimalEntity object.
     */
    AnimalEntity animalRequestDTOToAnimalEntity(AnimalRequestDTO animalRequestDTO);

    /**
     * Converts a list of AnimalEntity objects to a list of AnimalResponseDTO objects.
     *
     * @param animalEntities The list of AnimalEntity objects to convert.
     * @return The converted list of AnimalResponseDTO objects.
     */
    List<AnimalResponseDTO> animalEntityListToAnimalResponseDTOList(List<AnimalEntity> animalEntities);

    /**
     * Converts a list of AnimalRequestDTO objects to a list of AnimalEntity objects.
     *
     * @param animalRequestDTOList The list of AnimalRequestDTO objects to convert.
     * @return The converted list of AnimalEntity objects.
     */
    List<AnimalEntity> animalRequestDTOListToAnimalEntityList(List<AnimalRequestDTO> animalRequestDTOList);
}
