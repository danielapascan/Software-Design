package ro.ps.lab3.service.animalNeeds;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ro.ps.lab3.dto.*;
import ro.ps.lab3.exception.ExceptionCode;
import ro.ps.lab3.exception.NotFoundException;
import ro.ps.lab3.mapper.AnimalNeedsMapper;
import ro.ps.lab3.model.AnimalEntity;
import ro.ps.lab3.model.AnimalNeedsEntity;
import ro.ps.lab3.repository.AnimalNeedsRepository;
import ro.ps.lab3.repository.AnimalRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service implementation for managing animal needs.
 */
@Slf4j
@RequiredArgsConstructor
public class AnimalNeedsServiceBean implements AnimalNeedsService {

    private final AnimalNeedsRepository animalNeedsRepository;
    private final AnimalRepository animalRepository;
    private final AnimalNeedsMapper animalNeedsMapper;
    private final String applicationName;


    @Override
    public List<AnimalNeedsResponseDTO> getAnimalNeeds(String sortBy, Integer pageNumber, Integer pageSize) {
        log.info("Getting all animal needs for application {}", applicationName);
        Page<AnimalNeedsEntity> animalNeedsEntityList = animalNeedsRepository.findAllSorted(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)));
        return animalNeedsMapper.animalNeedsEntityListToAnimalNeeddsResponseDTOList(animalNeedsEntityList.getContent());
    }


    @Override
    @Transactional
    public AnimalNeedsResponseDTO saveAnimalNeeds(UUID id, AnimalNeedsRequestDTO animalNeedsRequestDTO) {
        Optional<AnimalEntity> animalEntity = animalRepository.findById(id);
        AnimalNeedsEntity needsToBeAdded = animalNeedsMapper.animalNeedsRequestDTOToAnimalNeedsEntity(animalNeedsRequestDTO);
        animalEntity.get().setAnimalNeedsEntity(needsToBeAdded);
        AnimalNeedsEntity needsAdded = animalNeedsRepository.save(needsToBeAdded);
        return animalNeedsMapper.animalNeedsEntitytoAnimalNeedsResponseDTO(needsAdded);
    }


    @Override
    public boolean deleteAnimalNeeds(UUID id) {
        if (animalNeedsRepository.existsById(id)) {
            animalNeedsRepository.deleteById(id);
            return true;
        }
        throw new NotFoundException(String.format(
                ExceptionCode.ERR005_ANIMAL_NEEDS_NOT_FOUND.getMessage(), id));
    }

    @Override
    public AnimalNeedsResponseDTO updateAnimalNeeds(UUID id, AnimalNeedsRequestDTO animalNeedsRequestDTO) {
        AnimalNeedsEntity needToBeUpdated = animalNeedsMapper.animalNeedsRequestDTOToAnimalNeedsEntity(animalNeedsRequestDTO);
        AnimalNeedsEntity originalNeeds = animalNeedsRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format(
                ExceptionCode.ERR005_ANIMAL_NEEDS_NOT_FOUND.getMessage(), id)));
        AnimalNeedsEntity needUpdated;
        originalNeeds.setCleanAnimal(needToBeUpdated.getCleanAnimal());
        originalNeeds.setLove(needToBeUpdated.getLove());
        originalNeeds.setFood(needToBeUpdated.getFood());
        originalNeeds.setWalk(needToBeUpdated.getWalk());
        originalNeeds.setCleanCage(needToBeUpdated.getCleanCage());
        needUpdated = animalNeedsRepository.save(originalNeeds);
        return animalNeedsMapper.animalNeedsEntitytoAnimalNeedsResponseDTO(needUpdated);
    }

    @Override
    public AnimalNeedsResponseDTO findById(UUID id) {
        Optional<AnimalEntity> animalEntity = animalRepository.findById(id);
        UUID idToFind = animalEntity.get().getAnimalNeedsEntity().getId();
        return animalNeedsRepository.findById(idToFind)
                .map(animalNeedsMapper::animalNeedsEntitytoAnimalNeedsResponseDTO)
                .orElseThrow(() -> new NotFoundException(String.format(
                        ExceptionCode.ERR001_ANIMAL_NOT_FOUND.getMessage(),
                        id
                )));
    }
}
