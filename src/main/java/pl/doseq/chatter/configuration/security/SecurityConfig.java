package pl.doseq.chatter.configuration.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.doseq.chatter.configuration.SecurityCustomProperties;
import pl.doseq.chatter.user.service.UserService;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private UserService userDetailsService;
    private SecurityCustomProperties securityCustomProperties;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests(
                request -> {
                    try {
                        request.antMatchers("/user/auth").permitAll()
                                .anyRequest().authenticated()
                                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                .and()
                                .addFilterBefore(new JwtAuthorizationFilter(userDetailsService, securityCustomProperties), UsernamePasswordAuthenticationFilter.class)
                                .exceptionHandling()
                                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

        );
        return http.build();
    }

}
