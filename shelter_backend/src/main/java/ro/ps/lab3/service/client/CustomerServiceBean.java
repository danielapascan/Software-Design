package ro.ps.lab3.service.client;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ro.ps.lab3.dto.AdoptionResponseDTO;
import ro.ps.lab3.dto.donation.DonationResponseDTO;
import ro.ps.lab3.dto.person.CustomerRequestDTO;
import ro.ps.lab3.dto.person.CustomerResponseDTO;
import ro.ps.lab3.exception.ExceptionCode;
import ro.ps.lab3.exception.NotFoundException;
import ro.ps.lab3.mapper.AdoptionMapper;
import ro.ps.lab3.mapper.CustomerMapper;
import ro.ps.lab3.mapper.DonationMapper;
import ro.ps.lab3.model.AdoptionEntity;
import ro.ps.lab3.model.CustomerEntity;
import ro.ps.lab3.model.DonationEntity;
import ro.ps.lab3.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ro.ps.lab3.security.util.SecurityConstants.PASSWORD_STRENGTH;

/**
 * Service implementation for managing customers.
 */
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceBean implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final DonationMapper donationMapper;
    private final AdoptionMapper adoptionMapper;

    @Override
    public Page<CustomerResponseDTO> getCustomers(String sortBy, Integer pageNumber, Integer pageSize) {
        Page<CustomerEntity> customerEntityList = customerRepository.findAllSorted(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)));
        List<CustomerResponseDTO> customerResponseDTOList = customerMapper.customerEntityListToCustomerResponseDTOList(customerEntityList.getContent());
        return new PageImpl<>(customerResponseDTOList, PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)), customerEntityList.getTotalElements());
    }

    @Override
    @Transactional
    public CustomerResponseDTO saveCustomer(CustomerRequestDTO customerRequestDTO) {
        CustomerEntity customerToBeAdded = customerMapper.customerRequestDTOToCustomerEntity(customerRequestDTO);
        CustomerEntity customerAdded = customerRepository.save(customerToBeAdded);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(PASSWORD_STRENGTH);
        String hashedPassword = customerToBeAdded.getPassword();
        hashedPassword = encoder.encode(hashedPassword);
        customerToBeAdded.setPassword(hashedPassword);
        return customerMapper.customerEntityToCustomerResponseDTO(customerAdded);
    }

    @Override
    public boolean deleteCustomer(UUID id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        throw new NotFoundException(String.format(
                ExceptionCode.ERR003_CUSTOMER_NOT_FOUND.getMessage(), id));
    }

    @Override
    public List<DonationResponseDTO> seeDonations(UUID id) {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
        List<DonationEntity> donationEntities = customerEntity.get().getDonationEntities();
        return donationMapper.donationEntityListToDonationResponseDTOList(donationEntities);
    }

    @Override
    public List<AdoptionResponseDTO> seeAdoptions(UUID id) {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
        List<AdoptionEntity> adoptionEntities = customerEntity.get().getAdoptionEntities();
        return adoptionMapper.adoptionEntityListToAdoptionResponseDTOList(adoptionEntities);
    }
}
