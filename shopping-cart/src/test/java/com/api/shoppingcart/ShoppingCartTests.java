package com.api.shoppingcart;

import com.api.shoppingcart.domain.dto.CartDto;
import com.api.shoppingcart.domain.services.CartService;
import com.api.shoppingcart.domain.services.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ShoppingCartTests {

	@Autowired
	ItemService itemService;

	@Autowired
	CartService cartService;

	@Test
	void checkout_ThreeIdenticalItems() throws Exception {
		CartDto cartDto = cartService.checkoutCart("6346da65062b2a61ad75dc28");
		assertTrue(cartDto.totalPriceWithDiscount.compareTo(BigDecimal.valueOf(25.98)) == 0);
	}

	@Test
	void checkout_TwoTShirtsTwoJeans() throws Exception {
		CartDto cartDto = cartService.checkoutCart("6346f079a7c953374505c5b1");
		assertTrue(cartDto.totalPriceWithDiscount.compareTo(BigDecimal.valueOf(62.99)) == 0);
	}

	@Test
	void checkout_OneTShirtsTwoJeansThreeDresses() throws Exception {
		CartDto cartDto = cartService.checkoutCart("6346f1f0a7c953374505c5b3");
		assertTrue(cartDto.totalPriceWithDiscount.compareTo(BigDecimal.valueOf(91.3)) == 0);
	}

	@Test
	void createCart() throws Exception {
		CartDto cartDto = cartService.createCart("12345678");
		assertTrue(cartDto.documentNumber.compareTo("12345678") == 0);
	}

	@Test
	void addItemIntoCart() throws Exception {
		CartDto cartDto = cartService.addItemIntoCart("6346d76fc2a561172dbc67d8",
				"6346bfa01c3d0be23964e84d");
		assertTrue(cartDto.items.
				stream().
				filter(item -> item.id.equals("6346bfa01c3d0be23964e84d")).
				count() == 2);
	}

	@Test
	void removeItemIntoCart() throws Exception {
		CartDto cartDto = cartService.removeItemFromCart("6346d96431dd9b738aa27555",
				"6346bfa01c3d0be23964e84d");
		assertTrue(cartDto.items.
				stream().
				filter(item -> item.id.equals("6346bfa01c3d0be23964e84d")).
				count() == 1);
	}

	@Test
	void getCart() throws Exception {
		CartDto cartDto = cartService.getCart("6346d96431dd9b738aa27555");
		assertTrue(cartDto != null);
	}

}
