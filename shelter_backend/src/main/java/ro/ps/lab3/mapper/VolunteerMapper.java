package ro.ps.lab3.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ro.ps.lab3.dto.person.VolunteerRequestDTO;
import ro.ps.lab3.dto.person.VolunteerResponseDTO;
import ro.ps.lab3.model.VolunteerEntity;

import java.util.List;

/**
 * Mapper interface for converting between VolunteerEntity and VolunteerDTO objects.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VolunteerMapper {
    /**
     * Converts a VolunteerEntity object to a VolunteerResponseDTO object.
     *
     * @param volunteerEntity The VolunteerEntity object to convert.
     * @return The converted VolunteerResponseDTO object.
     */
    VolunteerResponseDTO volunteerEntityToVolunteerResponseDTO(VolunteerEntity volunteerEntity);

    /**
     * Converts a VolunteerRequestDTO object to a VolunteerEntity object.
     *
     * @param volunteerRequestDTO The VolunteerRequestDTO object to convert.
     * @return The converted VolunteerEntity object.
     */
    VolunteerEntity volunteerRequestDTOToVolunteerEntity(VolunteerRequestDTO volunteerRequestDTO);

    /**
     * Converts a list of VolunteerEntity objects to a list of VolunteerResponseDTO objects.
     *
     * @param volunteerEntities The list of VolunteerEntity objects to convert.
     * @return The converted list of VolunteerResponseDTO objects.
     */
    List<VolunteerResponseDTO> volunteerEntityListToVolunteerResponseDTOList(List<VolunteerEntity> volunteerEntities);
}
