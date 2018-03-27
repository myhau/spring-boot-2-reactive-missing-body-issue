package spring.issue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {
    @Bean
    public WebClient client() {
        return WebClient.builder().baseUrl("http://localhost:8080").build();
    }
}

