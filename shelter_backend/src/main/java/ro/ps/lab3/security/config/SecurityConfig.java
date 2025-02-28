package ro.ps.lab3.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import ro.ps.lab3.exception.AccessDeniedHandlerBean;
import ro.ps.lab3.repository.PersonRepository;
import ro.ps.lab3.security.filter.AuthorizationFilter;
import ro.ps.lab3.security.filter.LoginFilter;
import ro.ps.lab3.security.service.PersonDetailsServiceBean;
import ro.ps.lab3.security.util.SecurityConstants;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    /**
     * Configures the security filter chain.
     *
     * @param http                HttpSecurity object.
     * @param loginFilter         LoginFilter bean.
     * @param authorizationFilter AuthorizationFilter bean.
     * @param accessDeniedHandler AccessDeniedHandler bean.
     * @return SecurityFilterChain object.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            LoginFilter loginFilter,
            AuthorizationFilter authorizationFilter,
            AccessDeniedHandler accessDeniedHandler
    ) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(handler -> handler.accessDeniedHandler(accessDeniedHandler))
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(SecurityConstants.AUTH_PATHS_TO_SKIP).permitAll()
                        .requestMatchers(SecurityConstants.SWAGGER_PATHS_TO_SKIP).permitAll()
                        .anyRequest().authenticated())
                .addFilter(loginFilter)
                .addFilterAfter(authorizationFilter, LoginFilter.class)
                .build();
    }

    /**
     * Provides the authentication manager bean.
     *
     * @param http               HttpSecurity object.
     * @param passwordEncoder    PasswordEncoder bean.
     * @param userDetailsService UserDetailsService bean.
     * @return AuthenticationManager object.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity http,
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService
    ) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

        return authenticationManagerBuilder.build();
    }

    /**
     * Provides the password encoder bean.
     *
     * @return PasswordEncoder object.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(SecurityConstants.PASSWORD_STRENGTH);
    }

    /**
     * Provides the user details service bean.
     *
     * @param personRepository PersonRepository object.
     * @return UserDetailsService object.
     */
    @Bean
    public UserDetailsService userDetailsService(PersonRepository personRepository) {
        return new PersonDetailsServiceBean(personRepository);
    }

    /**
     * Provides the authorization filter bean.
     *
     * @param objectMapper ObjectMapper object.
     * @return AuthorizationFilter object.
     */
    @Bean
    public AuthorizationFilter authorizationManager(ObjectMapper objectMapper) {
        return new AuthorizationFilter(objectMapper);
    }

    /**
     * Provides the login filter bean.
     *
     * @param objectMapper          ObjectMapper object.
     * @param authenticationManager AuthenticationManager object.
     * @return LoginFilter object.
     */
    @Bean
    public LoginFilter loginFilter(ObjectMapper objectMapper, AuthenticationManager authenticationManager) {
        return new LoginFilter(objectMapper, authenticationManager);
    }

    /**
     * Provides the access denied handler bean.
     *
     * @param objectMapper ObjectMapper object.
     * @return AccessDeniedHandler object.
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler(ObjectMapper objectMapper) {
        return new AccessDeniedHandlerBean(objectMapper);
    }
}
