package com.dieva.items.mcsv.infrastructure.client.webclient;

import com.dieva.items.mcsv.application.service.ItemService;
import com.dieva.items.mcsv.domain.model.Item;
import com.dieva.libs.mcsv.commons.domain.model.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductWebClientAdapter implements ItemService {

    private final WebClient.Builder webClient;

    @Override
    public List<Item> getAllProducts() {
        return this.webClient.build()
                .get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ProductDto.class)
                .map(product -> new Item(product, new Random().nextInt(10) + 1))
                .collectList()
                .block();
    }

    @Override
    public Optional<Item> getProductById(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return Optional.ofNullable(webClient.build().get().uri("/{id}", params)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .map(product -> new Item(product, new Random().nextInt(10) + 1))
                .block());


    }

    @Override
    public Optional<Item> saveItem(ProductDto productItem) {

        return Optional.ofNullable(webClient.build().post()
                .bodyValue(productItem)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .map(product -> new Item(product, new Random().nextInt(10) + 1))
                .block());
    }

    @Override
    public Optional<Item> updateItem(ProductDto productItem, Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return Optional.ofNullable(webClient.build().put().uri("/{id}", params)
                .bodyValue(productItem)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .map(product -> new Item(product, new Random().nextInt(10) + 1))
                .block());
    }

    @Override
    public void deleteItemById(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        webClient.build().delete().uri("/{id}", params)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
