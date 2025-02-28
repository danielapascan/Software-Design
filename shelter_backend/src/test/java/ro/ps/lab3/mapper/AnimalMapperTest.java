package ro.ps.lab3.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;
import ro.ps.lab3.dto.AnimalRequestDTO;
import ro.ps.lab3.dto.AnimalResponseDTO;
import ro.ps.lab3.model.AnimalEntity;

import java.util.List;
import java.util.UUID;

@SpringBootTest
public class AnimalMapperTest {
    private AnimalMapper underTest;

    private static final UUID ANIMAL_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    @BeforeEach
    void setUp() {
        underTest = Mappers.getMapper(AnimalMapper.class);
    }

    @Test
    void givenValidAnimalEntity_whenMapperCalled_thenReturnValidAnimalResponseDTO() {
        final var request = getAnimalEntity();
        final var expected = getAnimalResponseDTO();

        final var response = underTest.animalEntityToAnimalResponseDTO(request);

        assertThat(response).isEqualTo(expected);
    }

    @Test
    void givenValidAnimalRequestDTO_whenMapperCalled_thenReturnValidAnimalEntity() {
        final var request = getAnimalRequestDTO();
        final var expected = getAnimalEntity();
        expected.setId(null);

        final var response = underTest.animalRequestDTOToAnimalEntity(request);

        assertThat(response).isEqualTo(expected);
    }

    @Test
    void givenValidAnimalEntityList_whenMapperCalled_thenReturnValidAnimalResponseDTOList() {
        final var request = List.of(getAnimalEntity());
        final var expected = List.of(getAnimalResponseDTO());

        final var response = underTest.animalEntityListToAnimalResponseDTOList(request);

        assertThat(response).isEqualTo(expected);
    }

    @Test
    void givenValidAnimalRequestDTOList_whenMapperCalled_thenReturnValidAnimalEntityList() {
        final var request = List.of(getAnimalRequestDTO());
        final var expected = List.of(getAnimalEntity());
        expected.get(0).setId(null);

        final var response = underTest.animalRequestDTOListToAnimalEntityList(request);

        assertThat(response).isEqualTo(expected);
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

    private AnimalRequestDTO getAnimalRequestDTO() {
        return AnimalRequestDTO.builder()
                .name("Suzy")
                .age(5)
                .breed("Poodle")
                .gender("Female")
                .species("Dog")
                .build();
    }
}
