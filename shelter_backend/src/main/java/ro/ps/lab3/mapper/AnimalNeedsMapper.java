package ro.ps.lab3.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ro.ps.lab3.dto.AnimalNeedsRequestDTO;
import ro.ps.lab3.dto.AnimalNeedsResponseDTO;
import ro.ps.lab3.model.AnimalNeedsEntity;

import java.util.List;

/**
 * Mapper interface for converting between AnimalNeedsEntity and AnimalNeedsDTO objects.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnimalNeedsMapper {
    /**
     * Converts an AnimalNeedsEntity object to an AnimalNeedsResponseDTO object.
     *
     * @param animalNeedsEntity The AnimalNeedsEntity object to convert.
     * @return The converted AnimalNeedsResponseDTO object.
     */
    AnimalNeedsResponseDTO animalNeedsEntitytoAnimalNeedsResponseDTO(AnimalNeedsEntity animalNeedsEntity);

    /**
     * Converts an AnimalNeedsRequestDTO object to an AnimalNeedsEntity object.
     *
     * @param animalNeedsRequestDTO The AnimalNeedsRequestDTO object to convert.
     * @return The converted AnimalNeedsEntity object.
     */
    AnimalNeedsEntity animalNeedsRequestDTOToAnimalNeedsEntity(AnimalNeedsRequestDTO animalNeedsRequestDTO);

    /**
     * Converts a list of AnimalNeedsEntity objects to a list of AnimalNeedsResponseDTO objects.
     *
     * @param animalNeedsEntities The list of AnimalNeedsEntity objects to convert.
     * @return The converted list of AnimalNeedsResponseDTO objects.
     */
    List<AnimalNeedsResponseDTO> animalNeedsEntityListToAnimalNeeddsResponseDTOList(List<AnimalNeedsEntity> animalNeedsEntities);
}
