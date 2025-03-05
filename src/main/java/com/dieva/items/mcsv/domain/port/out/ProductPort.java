package com.dieva.items.mcsv.domain.port.out;

import com.dieva.items.mcsv.domain.model.Item;
import com.dieva.items.mcsv.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductPort {

    List<Item> getAllProducts();

    Optional<Item> getProductById(Long id);

    Optional<Item> saveProduct(Product productItem);

    Optional<Item> updateProduct(Product productItem, Long id);

    void deleteProductById(Long id);
}
