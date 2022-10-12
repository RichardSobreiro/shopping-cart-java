package com.api.shoppingcart.application;

import com.api.shoppingcart.domain.dto.ItemDto;
import com.api.shoppingcart.domain.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class ItemsController {

    @Autowired
    ItemService itemService;

    @GetMapping
    public @ResponseBody List<ItemDto> getItems(){
        return itemService.getAll();
    }
}
