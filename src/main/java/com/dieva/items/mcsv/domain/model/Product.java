package com.dieva.items.mcsv.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;
    private String name;
    private Double price;
    private LocalDateTime createdAt;
}
