package com.dieva.items.mcsv.application.service;

import com.dieva.items.mcsv.domain.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<Item> getAllProducts();

    Optional<Item> getProductById(Long id);
}
