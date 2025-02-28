package ro.ps.lab3.service.employee;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ro.ps.lab3.dto.person.EmployeeRequestDTO;
import ro.ps.lab3.dto.person.EmployeeResponseDTO;
import ro.ps.lab3.exception.ExceptionCode;
import ro.ps.lab3.exception.NotFoundException;
import ro.ps.lab3.mapper.EmployeeMapper;
import ro.ps.lab3.model.EmployeeEntity;
import ro.ps.lab3.repository.EmployeeRepository;

import java.util.List;
import java.util.UUID;

import static ro.ps.lab3.security.util.SecurityConstants.PASSWORD_STRENGTH;

/**
 * Service class for managing employees.
 */
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceBean implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public Page<EmployeeResponseDTO> getEmployees(String sortBy, Integer pageNumber, Integer pageSize) {
        Page<EmployeeEntity> employeeEntityList = employeeRepository.findAllSorted(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)));
        List<EmployeeResponseDTO> employeeResponseDTOList = employeeMapper.employeeEntityListToEmployeeResponseDTOList(employeeEntityList.getContent());
        return new PageImpl<>(employeeResponseDTOList, PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)), employeeEntityList.getTotalElements());
    }

    @Override
    @Transactional
    public EmployeeResponseDTO saveEmployee(EmployeeRequestDTO employeeRequestDTO) {
        EmployeeEntity employeeToBeAdded = employeeMapper.employeeRequestDTOToEmployeeEntity(employeeRequestDTO);
        EmployeeEntity employeeAdded = employeeRepository.save(employeeToBeAdded);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(PASSWORD_STRENGTH);
        String hashedPassword = employeeToBeAdded.getPassword();
        hashedPassword = encoder.encode(hashedPassword);
        employeeToBeAdded.setPassword(hashedPassword);
        return employeeMapper.employeeEntityToEmployeeResponseDTO(employeeAdded);
    }

    @Override
    public boolean deleteEmployee(UUID id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        throw new NotFoundException(String.format(
                ExceptionCode.ERR002_EMPLOYEE_NOT_FOUND.getMessage(), id));
    }

    @Override
    @Transactional
    public EmployeeResponseDTO giveRaise(UUID id, EmployeeRequestDTO employeeRequestDTO) {
        EmployeeEntity employeeToBeUpdated = employeeMapper.employeeRequestDTOToEmployeeEntity(employeeRequestDTO);
        EmployeeEntity originalEmployee = employeeRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format(
                ExceptionCode.ERR002_EMPLOYEE_NOT_FOUND.getMessage(), id)));
        EmployeeEntity employeeUpdated;
        originalEmployee.setSalary(employeeToBeUpdated.getSalary());
        employeeUpdated = employeeRepository.save(originalEmployee);
        return employeeMapper.employeeEntityToEmployeeResponseDTO(employeeUpdated);
    }
}
