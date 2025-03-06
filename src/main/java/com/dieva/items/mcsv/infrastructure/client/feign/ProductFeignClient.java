package com.dieva.items.mcsv.infrastructure.client.feign;

import com.dieva.libs.mcsv.commons.domain.model.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient( name = "${url.product.mcsv}")
public interface ProductFeignClient {

    @GetMapping
    List<ProductDto> getProducts();

    @GetMapping("/{id}")
    ProductDto getProductById(@PathVariable Long id);

    @PostMapping
    ProductDto saveProduct(@RequestBody ProductDto product);

    @PutMapping("/{id}")
    ProductDto updateProduct(@RequestBody ProductDto product, @PathVariable Long id);

    @DeleteMapping("/{id}")
    void deleteProductById(@PathVariable Long id);



}
