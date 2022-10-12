package com.api.shoppingcart.domain.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document("items")
public class Item {
    @Id
    public String id;
    public String name;
    public BigDecimal price;

    public Item() {
        super();
    }

    public Item(String name, BigDecimal price) {
        super();
        this.name = name;
        this.price = price;
    }
}
