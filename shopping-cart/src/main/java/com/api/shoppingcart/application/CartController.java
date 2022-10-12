package com.api.shoppingcart.application;

import com.api.shoppingcart.application.requests.CartAddItemRequest;
import com.api.shoppingcart.application.requests.CartCreateRequest;
import com.api.shoppingcart.application.requests.CartRemoveItemRequest;
import com.api.shoppingcart.domain.dto.CartDto;
import com.api.shoppingcart.domain.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping
    public CartDto createCart(@RequestBody CartCreateRequest request) throws Exception {
        return cartService.createCart(request.documentNumber);
    }

    @PutMapping("/{cart_id}/items/{item_id}")
    public CartDto updateCart(@PathVariable String cart_id, @PathVariable String item_id ) throws Exception {
        return cartService.addItemIntoCart(cart_id, item_id);
    }

    @DeleteMapping("/{cart_id}/items/{item_id}")
    public CartDto removeItemFromCart(@PathVariable String cart_id,
                                      @PathVariable String item_id) throws Exception{
        return cartService.removeItemFromCart(cart_id, item_id);
    }

    @GetMapping("/{cart_id}")
    public CartDto getCart(@PathVariable String cart_id) throws Exception{
        return cartService.getCart(cart_id);
    }

    @PostMapping("/{cart_id}/checkout")
    public CartDto checkoutCart(@PathVariable String cart_id) throws Exception{
        return cartService.checkoutCart(cart_id);
    }
}
