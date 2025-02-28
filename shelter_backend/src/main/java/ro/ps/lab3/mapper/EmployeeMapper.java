package ro.ps.lab3.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ro.ps.lab3.dto.person.EmployeeRequestDTO;
import ro.ps.lab3.dto.person.EmployeeResponseDTO;
import ro.ps.lab3.model.EmployeeEntity;

import java.util.List;

/**
 * Mapper interface for converting between EmployeeEntity and EmployeeDTO objects.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {
    /**
     * Converts an EmployeeEntity object to an EmployeeResponseDTO object.
     *
     * @param employeeEntity The EmployeeEntity object to convert.
     * @return The converted EmployeeResponseDTO object.
     */
    EmployeeResponseDTO employeeEntityToEmployeeResponseDTO(EmployeeEntity employeeEntity);

    /**
     * Converts an EmployeeRequestDTO object to an EmployeeEntity object.
     *
     * @param employeeRequestDTO The EmployeeRequestDTO object to convert.
     * @return The converted EmployeeEntity object.
     */
    EmployeeEntity employeeRequestDTOToEmployeeEntity(EmployeeRequestDTO employeeRequestDTO);

    /**
     * Converts a list of EmployeeEntity objects to a list of EmployeeResponseDTO objects.
     *
     * @param employeeEntities The list of EmployeeEntity objects to convert.
     * @return The converted list of EmployeeResponseDTO objects.
     */
    List<EmployeeResponseDTO> employeeEntityListToEmployeeResponseDTOList(List<EmployeeEntity> employeeEntities);
}
