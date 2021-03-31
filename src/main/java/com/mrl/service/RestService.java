package com.mrl.service;

import com.mrl.model.jsonplaceholder.User;
import com.mrl.model.pcfone.Quote;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class RestService {

    private final WebClient webClient;

    public Quote getQuoteSync() {
        return webClient.get().retrieve().bodyToMono(Quote.class).block();
    }

    public Flux<User> getJsonPlaceholderUsers() {
        return webClient.get().uri("users").retrieve().bodyToFlux(User.class);
    }

}
