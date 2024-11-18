package com.dieva.items.mcsv.infrastructure.client.webclient;

import com.dieva.items.mcsv.application.service.ItemService;
import com.dieva.items.mcsv.domain.model.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductWebClientAdapter implements ItemService {

    private final WebClient.Builder webClient;

    @Value("${url.product.mcsv}")
    private String url;

    @Override
    public List<Item> getAllProducts() {
        return this.webClient.build().get()
                .uri("http://".concat(url))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(new ParameterizedTypeReference<Item>() {})
                .collectList()
                .block();
    }

    @Override
    public Optional<Item> getProductById(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return Optional.ofNullable(this.webClient.build().get()
                .uri("http://".concat(url).concat("/{id}"), params)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Item.class)
                .block());


    }
}
