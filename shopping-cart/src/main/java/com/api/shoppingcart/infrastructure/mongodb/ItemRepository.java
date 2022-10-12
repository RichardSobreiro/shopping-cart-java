package com.api.shoppingcart.infrastructure.mongodb;

import com.api.shoppingcart.domain.entities.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ItemRepository extends MongoRepository<Item, String> {
    /*@Query(fields="{'id' : 1, 'name' : 1, 'price' : 1}")
    List<Item> findAll();

    public long count();*/
}
