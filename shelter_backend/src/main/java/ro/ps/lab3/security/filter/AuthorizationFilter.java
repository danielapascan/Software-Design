package ro.ps.lab3.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ro.ps.lab3.exception.ExceptionBody;
import ro.ps.lab3.security.util.JwtUtil;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Filter for handling authorization based on JWT tokens.
 */
@Slf4j
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    /**
     * Filters each incoming request to check for authorization based on JWT tokens.
     *
     * @param request     The HTTP servlet request.
     * @param response    The HTTP servlet response.
     * @param filterChain The filter chain.
     * @throws ServletException If a servlet exception occurs.
     * @throws IOException      If an I/O error occurs.
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String jwtToken = JwtUtil.getTokenFromRequest(request);
        if (jwtToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            Claims claims = (Claims) Jwts.parser()
                    .verifyWith((SecretKey) JwtUtil.getSigningKey(JwtUtil.secretKey))
                    .build()
                    .parse(jwtToken)
                    .getPayload();
            String email = claims.getSubject();
            Collection<SimpleGrantedAuthority> authorities = ((List<String>) claims.get("role")).stream()
                    .map(SimpleGrantedAuthority::new)
                    .toList();
            Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            this.onUnsuccessfulAuthorization(response, exception.getMessage());
        }
    }

    /**
     * Handles unsuccessful authorization by sending a forbidden response.
     *
     * @param response The HTTP servlet response.
     * @param message  The error message.
     */
    private void onUnsuccessfulAuthorization(HttpServletResponse response, String message) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());

        try {
            objectMapper.writeValue(response.getWriter(), new ExceptionBody(message));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}