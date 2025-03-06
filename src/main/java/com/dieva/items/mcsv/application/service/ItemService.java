package com.dieva.items.mcsv.application.service;

import com.dieva.items.mcsv.domain.model.Item;
import com.dieva.items.mcsv.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<Item> getAllProducts();

    Optional<Item> getProductById(Long id);

    Optional<Item> saveItem(Product productItem);

    Optional<Item> updateItem(Product productItem, Long id);

    void deleteItemById(Long id);
}
