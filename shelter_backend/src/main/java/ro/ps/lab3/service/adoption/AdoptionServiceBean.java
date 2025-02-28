package ro.ps.lab3.service.adoption;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ro.ps.lab3.dto.*;
import ro.ps.lab3.exception.ExceptionCode;
import ro.ps.lab3.exception.NotFoundException;
import ro.ps.lab3.mapper.AdoptionMapper;
import ro.ps.lab3.model.AdoptionEntity;
import ro.ps.lab3.model.AnimalEntity;
import ro.ps.lab3.model.PersonEntity;
import ro.ps.lab3.repository.AdoptionRepository;
import ro.ps.lab3.repository.AnimalRepository;
import ro.ps.lab3.repository.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service implementation for managing adoptions.
 */
@Slf4j
@RequiredArgsConstructor
public class AdoptionServiceBean implements AdoptionService {
    private final AdoptionMapper adoptionMapper;
    private final AdoptionRepository adoptionRepository;
    private final AnimalRepository animalRepository;
    private final PersonRepository personRepository;
    private final String applicationName;

    @Override
    public Page<AdoptionResponseDTO> getAdoptions(String sortBy, Integer pageNumber, Integer pageSize) {
        log.info("Getting all adoptions for application {}", applicationName);
        Page<AdoptionEntity> adoptionEntityList = adoptionRepository.findAllSorted(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)));
        List<AdoptionResponseDTO> adoptionResponseDTOList = adoptionMapper.adoptionEntityListToAdoptionResponseDTOList(adoptionEntityList.getContent());
        return new PageImpl<>(adoptionResponseDTOList, PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)), adoptionEntityList.getTotalElements());
    }

    @Override
    public AdoptionResponseDTO saveAdoption(UUID idUser, UUID idAnimal, AdoptionRequestDTO adoptionRequestDTO) {
        Optional<PersonEntity> userEntity = personRepository.findById(idUser);
        Optional<AnimalEntity> animalEntity = animalRepository.findById(idAnimal);
        if (animalEntity.get().getAdoptionEntity() != null) {
            throw new NotFoundException(String.format(
                    ExceptionCode.ERR009_INVALID_ADOPTION.getMessage(), idAnimal));
        } else {
            AdoptionEntity adoptionToBeSaved = adoptionMapper.adoptionRequestDTOToAdoptionEntity(adoptionRequestDTO);
            adoptionToBeSaved.setPersonEntity(userEntity.get());
            animalEntity.get().setAdoptionEntity(adoptionToBeSaved);
            AdoptionEntity adoptionSaved = adoptionRepository.save(adoptionToBeSaved);
            return adoptionMapper.adoptionEntityToAdoptionResponseDTO(adoptionSaved);
        }
    }

    @Override
    public boolean deleteAdoption(UUID id) {
        if (adoptionRepository.existsById(id)) {
            adoptionRepository.deleteById(id);
            return true;
        }
        throw new NotFoundException(String.format(
                ExceptionCode.ERR006_ADOPTION_NOT_FOUND.getMessage(), id));
    }

    @Override
    @Transactional
    public AdoptionResponseDTO updateAdoption(UUID id, AdoptionRequestDTO adoptionRequestDTO) {
        AdoptionEntity adoptionToBeUpdated = adoptionMapper.adoptionRequestDTOToAdoptionEntity(adoptionRequestDTO);
        AdoptionEntity originalAdoption = adoptionRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format(
                ExceptionCode.ERR006_ADOPTION_NOT_FOUND.getMessage(), id)));
        AdoptionEntity adoptionUpdated;
        originalAdoption.setLocalDate(adoptionToBeUpdated.getLocalDate());
        adoptionUpdated = adoptionRepository.save(originalAdoption);
        return adoptionMapper.adoptionEntityToAdoptionResponseDTO(adoptionUpdated);
    }
}
