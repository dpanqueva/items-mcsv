package com.dieva.items.mcsv.application.service;

import com.dieva.items.mcsv.domain.model.Item;
import com.dieva.libs.mcsv.commons.domain.model.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<Item> getAllProducts();

    Optional<Item> getProductById(Long id);

    Optional<Item> saveItem(ProductDto productItem);

    Optional<Item> updateItem(ProductDto productItem, Long id);

    void deleteItemById(Long id);
}
