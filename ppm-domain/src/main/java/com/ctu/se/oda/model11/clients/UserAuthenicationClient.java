package com.ctu.se.oda.model11.clients;

import com.ctu.se.oda.model11.models.dtos.UserDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class UserAuthenicationClient {
    private final WebClient webClient;

    public UserAuthenicationClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://103.221.220.183:8082").build();
    }

    public UserDTO login(String basicAuth) {
        return this.webClient.get().uri("/user/login").header(HttpHeaders.AUTHORIZATION, basicAuth).retrieve().bodyToMono(UserDTO.class).block();
    }

    public UserDTO getUserById(UUID userId) {
        return this.webClient.get().uri("/user/{userId}", userId).retrieve().bodyToMono(UserDTO.class).block();
    }
}
