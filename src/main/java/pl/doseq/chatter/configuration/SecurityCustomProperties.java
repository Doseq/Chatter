package pl.doseq.chatter.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "custom.security")
@Setter
@Getter
public class SecurityCustomProperties {

    private long jwtTokenExpirationTime;
    private String jwtTokenSecret;

}
