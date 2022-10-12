package com.api.shoppingcart.domain.services;

import com.api.shoppingcart.domain.dto.CartDto;
import com.api.shoppingcart.domain.dto.ItemDto;
import com.api.shoppingcart.domain.entities.Cart;
import com.api.shoppingcart.domain.entities.Item;
import com.api.shoppingcart.infrastructure.mongodb.CartRepository;
import com.api.shoppingcart.infrastructure.mongodb.ItemRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemRepository itemRepository;

    ModelMapper mapper = new ModelMapper();

    public CartService(){
        this.mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    public CartDto createCart(String documentNumber) throws Exception{
        Cart cartEntity = new Cart(documentNumber);
        cartEntity = cartRepository.save(cartEntity);
        CartDto cartDto = this.mapper.map(cartEntity, CartDto.class);
        cartDto.CalculateTotalPrice();
        return cartDto;
    }

    public CartDto addItemIntoCart(String id, String itemId) throws Exception{
        if(id.isEmpty()){
            throw new Exception("Cart does not exist!");
        }
        if(!cartRepository.existsById(id)){
            throw new Exception("Cart does not exist!");
        }
        if(itemId.isEmpty()){
            throw new Exception("Item does not exist!");
        }
        if(!itemRepository.existsById(itemId)){
            throw new Exception("Item does not exist!");
        }
        Cart cartEntity = cartRepository.findById(id).get();
        Item itemFromDb = itemRepository.findById(itemId).get();
        cartEntity.addItems(itemFromDb);
        cartRepository.save(cartEntity);
        cartEntity = cartRepository.findById(id).get();
        CartDto cartDto = this.mapper.map(cartEntity, CartDto.class);
        cartDto.CalculateTotalPrice();
        return cartDto;
    }

    public CartDto removeItemFromCart(String id, String itemId) throws Exception{
        if(id.isEmpty()){
            throw new Exception("Cart does not exist!");
        }
        if(!cartRepository.existsById(id)){
            throw new Exception("Cart does not exist!");
        }
        if(itemId.isEmpty()){
            throw new Exception("Item does not exist!");
        }
        if(!itemRepository.existsById(itemId)){
            throw new Exception("Item does not exist!");
        }
        Cart cartEntity = cartRepository.findById(id).get();
        if(!cartEntity.items.stream().anyMatch(item -> item.id.equals(itemId))){
            throw new Exception("Item does not exist within the given cart!");
        }
        Item itemFromDb = itemRepository.findById(itemId).get();
        cartEntity.removeItem(itemFromDb.id);
        cartRepository.save(cartEntity);
        cartEntity = cartRepository.findById(id).get();
        CartDto cartDto = this.mapper.map(cartEntity, CartDto.class);
        cartDto.CalculateTotalPrice();
        return cartDto;
    }

    public CartDto getCart(String id) throws Exception{
        if(id.isEmpty()){
            throw new Exception("Cart does not exist!");
        }
        if(!cartRepository.existsById(id)){
            throw new Exception("Cart does not exist!");
        }
        Cart cartEntity = cartRepository.findById(id).get();
        CartDto cartDto = this.mapper.map(cartEntity, CartDto.class);
        cartDto.CalculateTotalPrice();
        return cartDto;
    }

    public CartDto checkoutCart(String id) throws Exception{
        if(id.isEmpty()){
            throw new Exception("Cart does not exist!");
        }
        if(!cartRepository.existsById(id)){
            throw new Exception("Cart does not exist!");
        }
        Cart cartEntity = cartRepository.findById(id).get();
        if(cartEntity.items.isEmpty()){
            throw new Exception("Cart's items list is empty!");
        }
        CartDto cartDto = this.mapper.map(cartEntity, CartDto.class);
        cartDto.CalculateTotalPriceWithDiscount();
        cartDto.CalculateTotalPrice();
        return cartDto;
    }
}
