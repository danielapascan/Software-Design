package ro.ps.lab3.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ro.ps.lab3.dto.donation.DonationRequestDTO;
import ro.ps.lab3.dto.donation.DonationResponseDTO;
import ro.ps.lab3.model.DonationEntity;

import java.util.List;

/**
 * Mapper interface for converting between DonationEntity and DonationDTO objects.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DonationMapper {

    /**
     * Converts a DonationRequestDTO object to a DonationEntity object.
     *
     * @param donationRequestDTO The DonationRequestDTO object to convert.
     * @return The converted DonationEntity object.
     */
    DonationEntity donationRequestDTOToDonationEntity(DonationRequestDTO donationRequestDTO);

    /**
     * Converts a DonationEntity object to a DonationResponseDTO object.
     *
     * @param donationEntity The DonationEntity object to convert.
     * @return The converted DonationResponseDTO object.
     */
    DonationResponseDTO donationEntityToDonationResponseDTO(DonationEntity donationEntity);

    /**
     * Converts a list of DonationEntity objects to a list of DonationResponseDTO objects.
     *
     * @param donationEntityList The list of DonationEntity objects to convert.
     * @return The converted list of DonationResponseDTO objects.
     */
    List<DonationResponseDTO> donationEntityListToDonationResponseDTOList(List<DonationEntity> donationEntityList);

    /**
     * Converts a list of DonationRequestDTO objects to a list of DonationEntity objects.
     *
     * @param donationRequestDTOList The list of DonationRequestDTO objects to convert.
     * @return The converted list of DonationEntity objects.
     */
    List<DonationEntity> donationRequestDTOListToDonationEntityList(List<DonationRequestDTO> donationRequestDTOList);
}
