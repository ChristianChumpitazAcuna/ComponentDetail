package dev.jesus.component_detail_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient client(WebClient.Builder builder) {
        return builder
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}
