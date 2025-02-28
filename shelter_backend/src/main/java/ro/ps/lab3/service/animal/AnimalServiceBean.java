package ro.ps.lab3.service.animal;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ro.ps.lab3.dto.*;
import ro.ps.lab3.exception.NotFoundException;
import ro.ps.lab3.mapper.AnimalMapper;
import ro.ps.lab3.model.AnimalEntity;
import ro.ps.lab3.repository.AnimalRepository;
import ro.ps.lab3.exception.ExceptionCode;

import java.util.List;
import java.util.UUID;

/**
 * Service implementation for managing animals.
 */
@Slf4j
@RequiredArgsConstructor
public class AnimalServiceBean implements AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapperImpl;
    private final String applicationName;

    @Override
    public Page<AnimalResponseDTO> getAnimals(String searchBy, String sortBy, Integer pageNumber, Integer pageSize) {
        log.info("Getting all animals for application {}", applicationName);
        Page<AnimalEntity> animalEntityList = animalRepository.findAllSortedByAge(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)), searchBy);
        List<AnimalResponseDTO> animalResponseDTOList = animalMapperImpl.animalEntityListToAnimalResponseDTOList(animalEntityList.getContent());
        return new PageImpl<>(animalResponseDTOList, PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)), animalEntityList.getTotalElements());
    }

    @Override
    @Transactional
    public AnimalResponseDTO saveAnimal(AnimalRequestDTO animalRequestDTO) {
        AnimalEntity animalToBeAdded = animalMapperImpl.animalRequestDTOToAnimalEntity(animalRequestDTO);
        AnimalEntity animalAdded = animalRepository.save(animalToBeAdded);
        return animalMapperImpl.animalEntityToAnimalResponseDTO(animalAdded);
    }

    @Override
    public boolean deleteAnimal(UUID id) {
        if (animalRepository.existsById(id)) {
            animalRepository.deleteById(id);
            return true;
        }
        throw new NotFoundException(String.format(ExceptionCode.ERR001_ANIMAL_NOT_FOUND.getMessage(), id));
    }

    @Override
    @Transactional
    public AnimalResponseDTO updateAnimal(UUID id, AnimalRequestDTO animalRequestDTO) {
        AnimalEntity animalToBeUpdated = animalMapperImpl.animalRequestDTOToAnimalEntity(animalRequestDTO);
        AnimalEntity originalAnimal = animalRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format(ExceptionCode.ERR001_ANIMAL_NOT_FOUND.getMessage(), id)));
        AnimalEntity animalUpdated;
        originalAnimal.setSpecies(animalToBeUpdated.getSpecies());
        originalAnimal.setName(animalToBeUpdated.getName());
        originalAnimal.setAge(animalToBeUpdated.getAge());
        originalAnimal.setGender(animalToBeUpdated.getGender());
        originalAnimal.setBreed(animalToBeUpdated.getBreed());
        animalUpdated = animalRepository.save(originalAnimal);
        return animalMapperImpl.animalEntityToAnimalResponseDTO(animalUpdated);
    }

    @Override
    public AnimalResponseDTO findByName(String name) {
        return animalMapperImpl.animalEntityToAnimalResponseDTO(animalRepository.findByName(name));
    }

    @Override
    public AnimalResponseDTO findById(UUID id) {
        return animalRepository
                .findById(id)
                .map(animalMapperImpl::animalEntityToAnimalResponseDTO)
                .orElseThrow(() -> new NotFoundException(String.format(
                        ExceptionCode.ERR001_ANIMAL_NOT_FOUND.getMessage(),
                        id)));
    }
}
