package com.dieva.items.mcsv.infrastructure.client.feign;

import com.dieva.items.mcsv.domain.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient( name = "${url.product.mcsv}")
public interface ProductFeignClient {

    @GetMapping
    List<Product> getProducts();

    @GetMapping("/{id}")
    Product getProductById(@PathVariable Long id);

    @PostMapping
    Product saveProduct(@RequestBody Product product);



}
