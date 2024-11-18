package com.dieva.items.mcsv.domain.port.out;

import com.dieva.items.mcsv.domain.model.Item;

import java.util.List;
import java.util.Optional;

public interface ProductPort {

    List<Item> getAllProducts();

    Optional<Item> getProductById(Long id);
}
