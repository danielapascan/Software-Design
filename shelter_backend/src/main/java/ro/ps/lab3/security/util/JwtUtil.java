package ro.ps.lab3.security.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Properties;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;

/**
 * Utility class for JWT token generation and parsing.
 */
@Slf4j
@UtilityClass
public class JwtUtil {

    public String secretKey;
    public Integer tokenExpirationDays;

    static {
        try (InputStream inputStream = JwtUtil.class.getResourceAsStream("/application.yaml")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            JwtUtil.secretKey = properties.getProperty("secret-key");
            JwtUtil.tokenExpirationDays = Integer.parseInt(properties.getProperty("token-expiration-days"));
        } catch (IOException | NumberFormatException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Generates a JWT token.
     *
     * @param email The email address.
     * @param role  The collection of roles.
     * @return The generated JWT token.
     */
    public String generateJwtToken(String email, Collection<? extends GrantedAuthority> role) {
        return Jwts.builder()
                .subject(email)
                .claim("role", role.stream().map(GrantedAuthority::getAuthority).toList())
                .expiration(JwtUtil.getExpirationDate(JwtUtil.tokenExpirationDays))
                .signWith(JwtUtil.getSigningKey(JwtUtil.secretKey))
                .compact();
    }

    /**
     * Builds a cookie.
     *
     * @param cookieName  The name of the cookie.
     * @param cookieValue The value of the cookie.
     * @return The built cookie.
     */
    public Cookie buildCookie(String cookieName, String cookieValue) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setPath("/");
        cookie.setMaxAge((int) JwtUtil.getExpirationDate(JwtUtil.tokenExpirationDays).getTime());

        return cookie;
    }

    /**
     * Retrieves the JWT token from the HTTP request.
     *
     * @param request The HTTP servlet request.
     * @return The JWT token.
     */
    public String getTokenFromRequest(HttpServletRequest request) {
        return request.getHeader("Authorization") == null
                ? null
                : request.getHeader("Authorization").split("Bearer ")[1];
    }

    /**
     * Gets the expiration date of the JWT token.
     *
     * @param tokenExpirationDays The number of days until expiration.
     * @return The expiration date.
     */
    private Date getExpirationDate(Integer tokenExpirationDays) {
        return Date.valueOf(LocalDate.now().plusDays(tokenExpirationDays));
    }

    /**
     * Gets the signing key for JWT token.
     *
     * @param secretKey The secret key.
     * @return The signing key.
     */
    public Key getSigningKey(String secretKey) {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}