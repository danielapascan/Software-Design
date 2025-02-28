package ro.ps.lab3.service.volunteer;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ro.ps.lab3.dto.person.VolunteerRequestDTO;
import ro.ps.lab3.dto.person.VolunteerResponseDTO;
import ro.ps.lab3.exception.ExceptionCode;
import ro.ps.lab3.exception.NotFoundException;
import ro.ps.lab3.mapper.VolunteerMapper;
import ro.ps.lab3.model.VolunteerEntity;
import ro.ps.lab3.repository.VolunteerRepository;

import java.util.List;
import java.util.UUID;

import static ro.ps.lab3.security.util.SecurityConstants.PASSWORD_STRENGTH;

/**
 * Service class for managing volunteers.
 */
@Slf4j
@RequiredArgsConstructor
public class VolunteerServiceBean implements VolunteerService {
    private final VolunteerRepository volunteerRepository;
    private final VolunteerMapper volunteerMapper;

    @Override
    public Page<VolunteerResponseDTO> getVolunteers(String sortBy, Integer pageNumber, Integer pageSize) {
        Page<VolunteerEntity> volunteerEntityList = volunteerRepository.findAllSorted(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)));
        List<VolunteerResponseDTO> volunteerResponseDTOList = volunteerMapper.volunteerEntityListToVolunteerResponseDTOList(volunteerEntityList.getContent());
        return new PageImpl<>(volunteerResponseDTOList, PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)), volunteerEntityList.getTotalElements());
    }

    @Override
    @Transactional
    public VolunteerResponseDTO saveVolunteer(VolunteerRequestDTO volunteerRequestDTO) {
        VolunteerEntity volunteerToBeAdded = volunteerMapper.volunteerRequestDTOToVolunteerEntity(volunteerRequestDTO);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(PASSWORD_STRENGTH);
        String hashedPassword = volunteerToBeAdded.getPassword();
        hashedPassword = encoder.encode(hashedPassword);
        volunteerToBeAdded.setPassword(hashedPassword);
        VolunteerEntity volunteerAdded = volunteerRepository.save(volunteerToBeAdded);
        return volunteerMapper.volunteerEntityToVolunteerResponseDTO(volunteerAdded);
    }

    @Override
    public boolean deleteVolunteer(UUID id) {
        if (volunteerRepository.existsById(id)) {
            volunteerRepository.deleteById(id);
            return true;
        }
        throw new NotFoundException(String.format(
                ExceptionCode.ERR004_VOLUNTEER_NOT_FOUND.getMessage(), id));
    }
}
