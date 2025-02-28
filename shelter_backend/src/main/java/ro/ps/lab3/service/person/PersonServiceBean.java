package ro.ps.lab3.service.person;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ro.ps.lab3.dto.person.PersonResponseDTO;
import ro.ps.lab3.exception.ExceptionCode;
import ro.ps.lab3.exception.NotFoundException;
import ro.ps.lab3.mapper.PersonMapper;
import ro.ps.lab3.model.PersonEntity;
import ro.ps.lab3.repository.PersonRepository;

import java.util.List;

/**
 * Service class for managing persons.
 */
@Slf4j
@RequiredArgsConstructor
public class PersonServiceBean implements PersonService {
    private final PersonMapper personMapper;
    private final PersonRepository personRepository;

    @Override
    public List<PersonResponseDTO> getPersons(String searchBy, Integer pageNumber, Integer pageSize) {
        Page<PersonEntity> personEntityList = personRepository.findAll(PageRequest.of(pageNumber, pageSize),
                searchBy
        );
        return personMapper.userEntityListToUserResponseDTOList(personEntityList.getContent());
    }

    @Override
    public PersonResponseDTO findByEmail(String email) {
        return personRepository.findByEmail(email)
                .map(personMapper::userEntityToUserResponseDTO)
                .orElseThrow(() -> new NotFoundException(String.format(
                        ExceptionCode.ERR008_EMAIL_NOT_FOUND.getMessage(),
                        email
                )));
    }
}
