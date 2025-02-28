package ro.ps.lab3.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ro.ps.lab3.dto.AdoptionRequestDTO;
import ro.ps.lab3.dto.AdoptionResponseDTO;
import ro.ps.lab3.model.AdoptionEntity;

import java.util.List;

/**
 * Mapper interface for converting between AdoptionEntity and AdoptionDTO objects.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdoptionMapper {
    /**
     * Converts an AdoptionRequestDTO object to an AdoptionEntity object.
     *
     * @param adoptionRequestDTO The AdoptionRequestDTO object to convert.
     * @return The converted AdoptionEntity object.
     */
    AdoptionEntity adoptionRequestDTOToAdoptionEntity(AdoptionRequestDTO adoptionRequestDTO);

    /**
     * Converts an AdoptionEntity object to an AdoptionResponseDTO object.
     *
     * @param adoptionEntity The AdoptionEntity object to convert.
     * @return The converted AdoptionResponseDTO object.
     */
    AdoptionResponseDTO adoptionEntityToAdoptionResponseDTO(AdoptionEntity adoptionEntity);

    /**
     * Converts a list of AdoptionEntity objects to a list of AdoptionResponseDTO objects.
     *
     * @param adoptionEntityList The list of AdoptionEntity objects to convert.
     * @return The converted list of AdoptionResponseDTO objects.
     */
    List<AdoptionResponseDTO> adoptionEntityListToAdoptionResponseDTOList(List<AdoptionEntity> adoptionEntityList);
}
