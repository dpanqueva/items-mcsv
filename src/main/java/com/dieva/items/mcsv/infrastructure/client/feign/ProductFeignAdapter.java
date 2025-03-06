package com.dieva.items.mcsv.infrastructure.client.feign;

import com.dieva.items.mcsv.domain.model.Item;
import com.dieva.items.mcsv.domain.model.Product;
import com.dieva.items.mcsv.domain.port.out.ProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ProductFeignAdapter implements ProductPort {

    private final ProductFeignClient productFeignClient;

    @Override
    public List<Item> getAllProducts() {
        return productFeignClient.getProducts()
                .stream()
                .map(p -> new Item(p, 1)).toList();
    }

    @Override
    public Optional<Item> getProductById(Long id) {
        return Optional.of(Item.builder()
                .product(productFeignClient.getProductById(id))
                .quantity(1)
                .build());
    }

    @Override
    public Optional<Item> saveProduct(Product item) {
        return Optional.of(Item.builder()
                .product(productFeignClient.saveProduct(item))
                .quantity(5)
                .build());
    }

    @Override
    public Optional<Item> updateProduct(Product item, Long id) {
        return Optional.of(Item.builder()
                .product(productFeignClient.updateProduct(item, id))
                .quantity(1)
                .build());
    }

    @Override
    public void deleteProductById(Long id) {
        productFeignClient.deleteProductById(id);
    }
}
