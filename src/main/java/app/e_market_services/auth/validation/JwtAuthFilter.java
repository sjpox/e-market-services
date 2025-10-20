package app.e_market_services.auth.validation;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import app.e_market_services.jwt.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        log.info("Starting JWT authentication filter");
        String authHeader = request.getHeader("Authorization");
        log.info("Authorization Header: {}", authHeader);

         // If no Authorization header or doesn't start with Bearer, skip filter
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        log.info("Extracted Token: {}", token);

         // ✅ Extract username from the token
        String username = null;
        try {
            username = jwtService.extractUsername(token);
            log.info("Extracted Username: {}", username);
        } catch (Exception ex) {
        }

        try {
            if (username != null && jwtService.isTokenValid(token)
                    && SecurityContextHolder.getContext().getAuthentication() == null) {
                log.info("Token is valid for user: {}", username);
                // ✅ Extract roles from the token
                List<String> roles = jwtService.extractRoles(token); // implement this
                log.info("Roles from token: {}", roles);
                List<SimpleGrantedAuthority> authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList();

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null,
                        authorities);
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch (Exception ex) {
            log.error("Error during token validation: {}", ex.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
