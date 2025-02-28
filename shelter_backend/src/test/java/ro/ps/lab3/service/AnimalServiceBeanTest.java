package ro.ps.lab3.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ro.ps.lab3.dto.AnimalResponseDTO;
import ro.ps.lab3.exception.NotFoundException;
import ro.ps.lab3.mapper.AnimalMapper;
import ro.ps.lab3.model.AnimalEntity;
import ro.ps.lab3.repository.AnimalRepository;
import ro.ps.lab3.service.animal.AnimalServiceBean;

import java.util.Optional;
import java.util.UUID;
@SpringBootTest
public class AnimalServiceBeanTest {

    private static final UUID ANIMAL_ID = UUID.randomUUID();

    private AnimalServiceBean underTest;

    @Mock
    private AnimalRepository animalRepositoryMock;

    @Mock
    private AnimalMapper animalMapperMock;

    @BeforeEach
    void setUp() {
        this.underTest = new AnimalServiceBean(
                animalRepositoryMock,
                animalMapperMock,
                null
        );
    }

    @Test
    void givenValidAnimalId_whenFindByIdIsCalled_thenReturnCAnimalResponseDTO() {
        final var animalEntity = getAnimalEntity();
        final var animalResponseDTO = getAnimalResponseDTO();
        when(animalRepositoryMock.findById(any(UUID.class))).thenReturn(Optional.of(animalEntity));
        when(animalMapperMock.animalEntityToAnimalResponseDTO(any(AnimalEntity.class))).thenReturn(animalResponseDTO);

        final var response = underTest.findById(ANIMAL_ID);

        assertThat(response).isEqualTo(animalResponseDTO);
        verify(animalRepositoryMock).findById(any(UUID.class));
        verify(animalMapperMock).animalEntityToAnimalResponseDTO(any(AnimalEntity.class));
    }

    @Test
    void givenInvalidAnimalId_whenFindByIdIsCalled_thenThrowException() {
        when(animalRepositoryMock.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.findById(ANIMAL_ID))
                .isInstanceOf(NotFoundException.class);
        verify(animalRepositoryMock).findById(any(UUID.class));
        verify(animalMapperMock, never()).animalEntityToAnimalResponseDTO(any(AnimalEntity.class));
    }

    private AnimalEntity getAnimalEntity() {
        return AnimalEntity.builder()
                .id(ANIMAL_ID)
                .name("Suzy")
                .age(5)
                .breed("Poodle")
                .gender("Female")
                .species("Dog")
                .build();
    }

    private AnimalResponseDTO getAnimalResponseDTO() {
        return AnimalResponseDTO.builder()
                .id(ANIMAL_ID)
                .name("Suzy")
                .age(5)
                .breed("Poodle")
                .gender("Female")
                .species("Dog")
                .build();
    }

}
