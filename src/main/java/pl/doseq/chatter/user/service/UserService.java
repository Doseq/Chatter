package pl.doseq.chatter.user.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.doseq.chatter.configuration.SecurityCustomProperties;
import pl.doseq.chatter.user.model.RegisterUserDto;
import pl.doseq.chatter.user.model.User;
import pl.doseq.chatter.user.repository.UserRepository;

import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private SecurityCustomProperties securityCustomProperties;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(username);
        if(user.isEmpty()) throw new UsernameNotFoundException(String.format("User with username: %s not found", username));
        return user.get();
    }

    public String authenticate(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);
        if(!passwordEncoder.matches(password, userDetails.getPassword()))
            throw new BadCredentialsException("Wrong password for user " + username);
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + securityCustomProperties.getJwtTokenExpirationTime()))
                .sign(Algorithm.HMAC384(securityCustomProperties.getJwtTokenSecret()));
    }

    public void addUser(RegisterUserDto registerUserDto) {
        User user = User.builder()
                .name(registerUserDto.getUsername())
                .password(passwordEncoder.encode(registerUserDto.getPassword()))
                .build();
        userRepository.save(user);
    }
}
