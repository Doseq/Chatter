package pl.doseq.chatter.configuration.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.doseq.chatter.configuration.SecurityCustomProperties;
import pl.doseq.chatter.user.service.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Log4j2
@AllArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private UserService userDetailsService;
    private SecurityCustomProperties securityCustomProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<UsernamePasswordAuthenticationToken> auth = getAuth(request);
        if(auth.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }
        logger.info("Successfully authenticated user " + auth.get().getName() + ", setting security context.");
        SecurityContextHolder.getContext().setAuthentication(auth.get());
        filterChain.doFilter(request, response);
    }

    private Optional<UsernamePasswordAuthenticationToken> getAuth(HttpServletRequest request) {
        Optional<UsernamePasswordAuthenticationToken> auth = Optional.empty();
        String token = request.getHeader("Authorization");
        if(token != null && token.startsWith("Bearer")) {
            String username = JWT.require(Algorithm.HMAC384(securityCustomProperties.getJwtTokenSecret()))
                    .build()
                    .verify(token.replace("Bearer ",""))
                    .getSubject();
            if(username != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                auth = Optional.of(new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities()));
            }
        }
        return auth;
    }

}
