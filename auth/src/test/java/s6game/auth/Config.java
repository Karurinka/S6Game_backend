package s6game.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("test")
public class Config {
    @Autowired
    private TestRestTemplate test;

    @Bean
    @Primary
    RestTemplate template(){
        return  test.getRestTemplate();
    }

}

