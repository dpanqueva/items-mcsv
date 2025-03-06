package com.dieva.items.mcsv.domain.port.out;

import com.dieva.items.mcsv.domain.model.Item;
import com.dieva.libs.mcsv.commons.domain.model.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductPort {

    List<Item> getAllProducts();

    Optional<Item> getProductById(Long id);

    Optional<Item> saveProduct(ProductDto productItem);

    Optional<Item> updateProduct(ProductDto productItem, Long id);

    void deleteProductById(Long id);
}
