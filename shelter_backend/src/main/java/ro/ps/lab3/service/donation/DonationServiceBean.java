package ro.ps.lab3.service.donation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ro.ps.lab3.dto.donation.DonationRequestDTO;
import ro.ps.lab3.dto.donation.DonationResponseDTO;
import ro.ps.lab3.exception.ExceptionCode;
import ro.ps.lab3.exception.NotFoundException;
import ro.ps.lab3.mapper.DonationMapper;
import ro.ps.lab3.model.DonationEntity;
import ro.ps.lab3.model.PersonEntity;
import ro.ps.lab3.repository.DonationRepository;
import ro.ps.lab3.repository.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service implementation for managing donations.
 */
@Slf4j
@RequiredArgsConstructor
public class DonationServiceBean implements DonationService {

    private final DonationRepository donationRepository;
    private final DonationMapper donationMapper;
    private final PersonRepository personRepository;
    private final String applicationName;


    @Override
    public Page<DonationResponseDTO> getDonations(String sortBy, Integer pageNumber, Integer pageSize) {
        log.info("Getting all donations for application {}", applicationName);
        Page<DonationEntity> donationEntityList = donationRepository.findAllSorted(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)));
        List<DonationResponseDTO> donationResponseDTOList = donationMapper.donationEntityListToDonationResponseDTOList(donationEntityList.getContent());
        return new PageImpl<>(donationResponseDTOList, PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)), donationEntityList.getTotalElements());
    }


    @Override
    @Transactional
    public DonationResponseDTO saveDonation(UUID id, DonationRequestDTO donationRequestDTO) {
        Optional<PersonEntity> userEntity = personRepository.findById(id);
        DonationEntity donationToBeAdded = donationMapper.donationRequestDTOToDonationEntity(donationRequestDTO);
        donationToBeAdded.setPersonEntity(userEntity.get());
        DonationEntity donationAdded = donationRepository.save(donationToBeAdded);
        return donationMapper.donationEntityToDonationResponseDTO(donationAdded);
    }

    @Override
    public boolean deleteDonation(UUID id) {
        if (donationRepository.existsById(id)) {
            donationRepository.deleteById(id);
            return true;
        }
        throw new NotFoundException(String.format(
                ExceptionCode.ERR007_DONATION_NOT_FOUND.getMessage(), id));
    }

    @Override
    @Transactional
    public DonationResponseDTO updateDonation(UUID id, DonationRequestDTO donationRequestDTO) {
        DonationEntity donationToBeUpdated = donationMapper.donationRequestDTOToDonationEntity(donationRequestDTO);
        DonationEntity originalDonation = donationRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format(
                ExceptionCode.ERR007_DONATION_NOT_FOUND.getMessage(), id)));
        DonationEntity donationUpdated;
        originalDonation.setAmount(donationToBeUpdated.getAmount());
        originalDonation.setCause(donationToBeUpdated.getCause());
        originalDonation.setLocalDate(donationToBeUpdated.getLocalDate());
        originalDonation.setDescription(donationToBeUpdated.getDescription());
        donationUpdated = donationRepository.save(originalDonation);
        return donationMapper.donationEntityToDonationResponseDTO(donationUpdated);
    }
}
