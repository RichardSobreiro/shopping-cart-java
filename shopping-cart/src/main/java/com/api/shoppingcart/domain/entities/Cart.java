package com.api.shoppingcart.domain.entities;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("carts")
public class Cart {

    @Id
    public String id;
    public String documentNumber;
    public List<Item> items;

    public Cart(String documentNumber) {
        super();
        this.documentNumber = documentNumber;
    }

    public void addItems(Item item){
        if(this.items == null)
            this.items = new ArrayList<>();
        this.items.add(item);
    }

    public void removeItem(String id){
        if(this.items == null)
            return;
        this.items.stream()
                .filter(item -> item.id.equals(id))  // Condition here
                .findFirst()
                .ifPresent(this.items::remove);
    }
}
