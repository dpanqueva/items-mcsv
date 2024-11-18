package com.dieva.items.mcsv.domain.model;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private Product product;

    private int quantity;

    public Double getTotal(){
        return product.getPrice() * quantity;
    }


}
