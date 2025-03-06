package com.dieva.items.mcsv.application.usecases;

import com.dieva.items.mcsv.application.service.ItemService;
import com.dieva.items.mcsv.domain.model.Item;
import com.dieva.items.mcsv.domain.model.Product;
import com.dieva.items.mcsv.domain.port.out.ProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final ProductPort productPort;


    @Override
    public List<Item> getAllProducts() {
        return productPort.getAllProducts();
    }

    @Override
    public Optional<Item> getProductById(Long id) {
        return productPort.getProductById(id);
    }

    @Override
    public Optional<Item> saveItem(Product productItem) {
        return productPort.saveProduct(productItem);
    }

    @Override
    public Optional<Item> updateItem(Product productItem, Long id) {
        return productPort.updateProduct(productItem, id);
    }

    @Override
    public void deleteItemById(Long id) {
        productPort.deleteProductById(id);
    }
}
