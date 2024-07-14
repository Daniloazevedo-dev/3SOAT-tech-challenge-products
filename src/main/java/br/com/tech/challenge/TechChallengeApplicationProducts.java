package br.com.tech.challenge;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class })
@Generated
public class TechChallengeApplicationProducts {

    public static void main(String[] args) {
        SpringApplication.run(TechChallengeApplicationProducts.class, args);
    }

}
