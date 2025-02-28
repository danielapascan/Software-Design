package ro.ps.lab3.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ro.ps.lab3.model.PersonEntity;
import ro.ps.lab3.repository.PersonRepository;
import ro.ps.lab3.exception.ExceptionCode;

/**
 * Service bean for loading user details during authentication.
 */
@RequiredArgsConstructor
public class PersonDetailsServiceBean implements UserDetailsService {

    private final PersonRepository personRepository;

    /**
     * Loads user details by username (email).
     *
     * @param email The email address of the user.
     * @return The UserDetails object representing the user.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return personRepository
                .findByEmail(email)
                .map(this::getUserDetails)
                .orElseThrow(() -> new BadCredentialsException(ExceptionCode.ERR099_INVALID_CREDENTIALS.getMessage()));
    }

    /**
     * Converts a PersonEntity object to a UserDetails object.
     *
     * @param user The PersonEntity object representing the user.
     * @return The UserDetails object representing the user.
     */
    private UserDetails getUserDetails(PersonEntity user) {
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}