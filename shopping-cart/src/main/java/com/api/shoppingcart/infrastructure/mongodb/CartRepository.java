package com.api.shoppingcart.infrastructure.mongodb;

import com.api.shoppingcart.domain.entities.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CartRepository extends MongoRepository<Cart, String> {

    @Query(value="{}", fields="{'documentNumber' : 1, 'items' : 1}")
    List<Cart> findAll();

    public long count();
}
