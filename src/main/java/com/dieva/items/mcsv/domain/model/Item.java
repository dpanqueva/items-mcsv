package com.dieva.items.mcsv.domain.model;

import com.dieva.libs.mcsv.commons.domain.model.ProductDto;
import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private ProductDto product;

    private int quantity;

    public Double getTotal(){
        return product.getPrice() * quantity;
    }


}
