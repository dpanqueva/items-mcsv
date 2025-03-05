package com.dieva.items.mcsv.infrastructure.controller;

import com.dieva.items.mcsv.application.service.ItemService;
import com.dieva.items.mcsv.domain.model.Item;
import com.dieva.items.mcsv.domain.model.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RefreshScope
@RestController
@Slf4j
public class ItemController {

    private final ItemService itemService;
    private final CircuitBreakerFactory breakerFactory;

    @Value("${config.text}")
    private String configText;

    @Autowired
    private Environment env;

    public ItemController(@Qualifier("productWebClientAdapter") ItemService itemService, CircuitBreakerFactory breakerFactory) {
        this.itemService = itemService;
        this.breakerFactory = breakerFactory;
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Optional<Item> itemOptional = breakerFactory.create("items")
                .run(()-> itemService.getProductById(id),
                        this::buildDefaultItemOptional
                );
        if(itemOptional.isPresent()) {
            return ResponseEntity.ok(itemOptional.get());
        }

        return itemOptional
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * This configuration apply for file application yml or properties
     * */
    @CircuitBreaker(name = "items", fallbackMethod = "buildFallBackItemOptional")
    @GetMapping("/v2/{id}")
    public ResponseEntity<Item> getItemByIdV2(@PathVariable Long id) {
        Optional<Item> itemOptional = itemService.getProductById(id);
        if(itemOptional.isPresent()) {
            return ResponseEntity.ok(itemOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @CircuitBreaker(name="items", fallbackMethod = "buildFallBackItemOptionalV3")
    @TimeLimiter(name = "items")
    @GetMapping("/v3/{id}")
    public CompletableFuture<?> getItemByIdV3(@PathVariable Long id) {
        return CompletableFuture.supplyAsync(()->{
            Optional<Item> itemOptional = itemService.getProductById(id);
            if(itemOptional.isPresent()) {
                return ResponseEntity.ok(itemOptional.get());
            }
            return ResponseEntity.notFound().build();
        });
    }

    @GetMapping("/fetch-config")
    public ResponseEntity<Map<String,Object>>fetchConfig(@Value("${server.port}") int port){
        Map<String,Object> json = new HashMap<String,Object>();
        json.put("text", configText);
        json.put("port", port);
        if(env.getActiveProfiles().length > 0){
            json.put("profile", env.getActiveProfiles()[0]);
            json.put("name", env.getProperty("config.autor.name"));
            json.put("email", env.getProperty("config.autor.email"));
        }
        return ResponseEntity.ok(json);
    }

    private Optional<Item> buildDefaultItemOptional(Throwable e){
        log.error(e.getMessage(), e);
        return Optional.of(buildItemDefaultResponse());
    }

    private ResponseEntity<Item> buildFallBackItemOptional(Throwable e){
        log.error(e.getMessage(), e);
        return ResponseEntity.ok().body(buildItemDefaultResponse());
    }

    private CompletableFuture<?> buildFallBackItemOptionalV3(Throwable e){
        log.error(e.getMessage(), e);
        return CompletableFuture.completedFuture(ResponseEntity.ok().body(buildItemDefaultResponse()));
    }

    private Item buildItemDefaultResponse(){
        return new Item(Product.builder()
                .id(1L)
                .name("Camara Sony")
                .price(3000.0)
                .createdAt(LocalDateTime.now().now())
                .build(),5);
    }
}
