package ro.ps.lab3.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ro.ps.lab3.dto.person.CustomerRequestDTO;
import ro.ps.lab3.dto.person.CustomerResponseDTO;
import ro.ps.lab3.model.CustomerEntity;

import java.util.List;

/**
 * Mapper interface for converting between CustomerEntity and CustomerDTO objects.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
    /**
     * Converts a CustomerEntity object to a CustomerResponseDTO object.
     *
     * @param customerEntity The CustomerEntity object to convert.
     * @return The converted CustomerResponseDTO object.
     */
    CustomerResponseDTO customerEntityToCustomerResponseDTO(CustomerEntity customerEntity);

    /**
     * Converts a CustomerRequestDTO object to a CustomerEntity object.
     *
     * @param customerRequestDTO The CustomerRequestDTO object to convert.
     * @return The converted CustomerEntity object.
     */
    CustomerEntity customerRequestDTOToCustomerEntity(CustomerRequestDTO customerRequestDTO);

    /**
     * Converts a list of CustomerEntity objects to a list of CustomerResponseDTO objects.
     *
     * @param customerEntities The list of CustomerEntity objects to convert.
     * @return The converted list of CustomerResponseDTO objects.
     */
    List<CustomerResponseDTO> customerEntityListToCustomerResponseDTOList(List<CustomerEntity> customerEntities);
}
