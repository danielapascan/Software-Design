package ro.ps.lab3.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ro.ps.lab3.dto.auth.LoginRequestDTO;
import ro.ps.lab3.exception.ExceptionBody;
import ro.ps.lab3.security.util.JwtUtil;
import ro.ps.lab3.security.util.SecurityConstants;

import java.io.IOException;

/**
 * Custom filter for handling user login/authentication.
 */
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;

    /**
     * Constructs a new LoginFilter instance.
     *
     * @param objectMapper          The ObjectMapper for JSON processing.
     * @param authenticationManager The AuthenticationManager for user authentication.
     */
    public LoginFilter(ObjectMapper objectMapper, AuthenticationManager authenticationManager) {
        this.objectMapper = objectMapper;
        this.setAuthenticationManager(authenticationManager);
        this.setFilterProcessesUrl(SecurityConstants.LOGIN_URL);
    }

    /**
     * Attempts authentication using the provided credentials.
     *
     * @param request  The HTTP servlet request.
     * @param response The HTTP servlet response.
     * @return The Authentication object if successful.
     * @throws AuthenticationException If authentication fails.
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            LoginRequestDTO authenticationRequest = objectMapper.readValue(
                    request.getInputStream(),
                    LoginRequestDTO.class
            );
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()
            );

            return super.getAuthenticationManager().authenticate(authentication);
        } catch (Exception e) {
            log.error(e.getMessage());

            throw new BadCredentialsException(e.getMessage());
        }
    }

    /**
     * Handles successful authentication by generating a JWT token.
     *
     * @param request    The HTTP servlet request.
     * @param response   The HTTP servlet response.
     * @param chain      The filter chain.
     * @param authResult The authentication result.
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) {
        String accessToken = JwtUtil.generateJwtToken(
                ((User) authResult.getPrincipal()).getUsername(),
                authResult.getAuthorities()
        );
        response.addCookie(JwtUtil.buildCookie(SecurityConstants.JWT_TOKEN, accessToken));

        response.setStatus(HttpStatus.OK.value());
    }

    /**
     * Handles unsuccessful authentication by sending an unauthorized response.
     *
     * @param request  The HTTP servlet request.
     * @param response The HTTP servlet response.
     * @param failed   The authentication exception.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed)
            throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        objectMapper.writeValue(response.getWriter(), new ExceptionBody(failed.getMessage()));
    }
}