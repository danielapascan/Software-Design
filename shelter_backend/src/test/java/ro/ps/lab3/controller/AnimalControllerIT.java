package ro.ps.lab3.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import ro.ps.lab3.dto.AnimalResponseDTO;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnimalControllerIT {

    private static String API_URL = "http://localhost:{PORT}/api/animals/all";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private Integer port;

    @Value("${security.invalid-jwt-token}")
    private String invalidJwtToken;

    @Value("${security.admin-jwt-token}")
    private String adminJwtToken;

    @BeforeEach
    public void setUp() {
        API_URL = API_URL.replace("{PORT}", port.toString());
    }

    @Test
    void givenInvalidJwtToken_whenFindByIdIsCalled_thenReturnAccessForbidden() {
        final var animalId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        final var headers = getHeadersEntity(invalidJwtToken);

        final var response = testRestTemplate.exchange(
                String.format("%s/%s", API_URL, animalId),
                HttpMethod.GET,
                headers,
                AnimalResponseDTO.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    void givenValidAnimalId_whenFindByIdIsCalled_thenReturnAnimalResponseDTO() {
        final var animalId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        final var headers = getHeadersEntity(adminJwtToken);
        final var expected = getAnimalResponseDTO();

        final var response = testRestTemplate.exchange(
                String.format("%s/%s", API_URL, animalId),
                HttpMethod.GET,
                headers,
                AnimalResponseDTO.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expected);
    }

    @Test
    void givenInvalidAnimalId_whenFindByIdIsCalled_thenThrowException() {
        final var animalId = UUID.fromString("99999999-9999-9999-9999-999999999999");
        final var headers = getHeadersEntity(adminJwtToken);

        final var response = testRestTemplate.exchange(
                String.format("%s/%s", API_URL, animalId),
                HttpMethod.GET,
                headers,
                AnimalResponseDTO.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private HttpEntity<?> getHeadersEntity(String jwtToken) {
        final var headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);

        return new HttpEntity<>(null, headers);
    }

    private AnimalResponseDTO getAnimalResponseDTO() {
        return AnimalResponseDTO.builder()
                .id(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .name("Suzy")
                .age(5)
                .breed("Poodle")
                .gender("Female")
                .species("Dog")
                .build();
    }

}
