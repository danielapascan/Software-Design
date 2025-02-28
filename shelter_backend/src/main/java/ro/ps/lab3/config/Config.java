package ro.ps.lab3.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import ro.ps.lab3.mapper.*;
import ro.ps.lab3.repository.*;
import ro.ps.lab3.service.adoption.AdoptionService;
import ro.ps.lab3.service.adoption.AdoptionServiceBean;
import ro.ps.lab3.service.animal.AnimalService;
import ro.ps.lab3.service.animal.AnimalServiceBean;
import ro.ps.lab3.service.animalNeeds.AnimalNeedsService;
import ro.ps.lab3.service.animalNeeds.AnimalNeedsServiceBean;
import ro.ps.lab3.service.client.CustomerService;
import ro.ps.lab3.service.client.CustomerServiceBean;
import ro.ps.lab3.service.donation.DonationService;
import ro.ps.lab3.service.donation.DonationServiceBean;
import ro.ps.lab3.service.employee.EmployeeService;
import ro.ps.lab3.service.employee.EmployeeServiceBean;
import ro.ps.lab3.service.mail.AsyncMailServiceBean;
import ro.ps.lab3.service.mail.MailService;
import ro.ps.lab3.service.mail.SyncMailServiceBean;
import ro.ps.lab3.service.person.PersonService;
import ro.ps.lab3.service.person.PersonServiceBean;
import ro.ps.lab3.service.volunteer.VolunteerService;
import ro.ps.lab3.service.volunteer.VolunteerServiceBean;

/**
 * A configuration class for initializing various beans used in the application.
 */
@Configuration
public class Config {
    @Bean
    public VolunteerService volunteerServiceBean(
            VolunteerRepository volunteerRepository,
            VolunteerMapper volunteerMapper
    ) {
        return new VolunteerServiceBean(volunteerRepository, volunteerMapper);
    }

    @Bean
    public EmployeeService employeeServiceBean(
            EmployeeRepository employeeRepository,
            EmployeeMapper employeeMapper
    ) {
        return new EmployeeServiceBean(employeeRepository, employeeMapper);
    }

    @Bean
    public CustomerService customerServiceBean(
            CustomerRepository customerRepository,
            CustomerMapper customerMapper,
            DonationMapper donationMapper,
            AdoptionMapper adoptionMapper
    ) {
        return new CustomerServiceBean(customerRepository, customerMapper, donationMapper, adoptionMapper);
    }

    @Bean
    public PersonService personServiceBean(
            PersonMapper personMapper,
            PersonRepository personRepository
    ) {
        return new PersonServiceBean(personMapper, personRepository);
    }

    @Bean
    public AnimalService animalServiceBean(
            AnimalRepository animalRepository,
            AnimalMapper animalMapper,
            @Value("${spring.application.name:BACKEND}") String applicationName
    ) {
        return new AnimalServiceBean(animalRepository, animalMapper, applicationName);
    }

    @Bean
    public AnimalNeedsService animalNeedsServiceBean(
            AnimalNeedsRepository animalNeedsRepository,
            AnimalRepository animalRepository,
            AnimalNeedsMapper animalNeedsMapper,
            @Value("${spring.application.name:BACKEND}") String applicationName
    ) {
        return new AnimalNeedsServiceBean(animalNeedsRepository, animalRepository, animalNeedsMapper, applicationName);
    }

    @Bean
    public AdoptionService adoptionServiceBean(
            AdoptionRepository adoptionRepository,
            AdoptionMapper adoptionMapper,
            AnimalRepository animalRepository,
            PersonRepository personRepository,
            @Value("${spring.application.name:BACKEND}") String applicationName
    ) {
        return new AdoptionServiceBean(adoptionMapper, adoptionRepository, animalRepository, personRepository, applicationName);
    }

    @Bean
    public DonationService donationServiceBean(
            DonationRepository donationRepository,
            DonationMapper donationMapper,
            PersonRepository personRepository,
            @Value("${spring.application.name:BACKEND}") String applicationName
    ) {
        return new DonationServiceBean(donationRepository, donationMapper, personRepository, applicationName);
    }

    @Bean
    public MailService syncMailServiceBean(
            @Value("${mail-sender-app.url}") String url,
            RestTemplateBuilder restTemplateBuilder
    ) {
        return new SyncMailServiceBean(url, restTemplateBuilder.build());
    }

    @Bean
    public MailService asyncMailServiceBean(
            @Value("${queues.async-mail-sender-request}") String destination,
            JmsTemplate jmsTemplate,
            ObjectMapper objectMapper
    ) {
        return new AsyncMailServiceBean(destination, jmsTemplate, objectMapper);
    }
}
