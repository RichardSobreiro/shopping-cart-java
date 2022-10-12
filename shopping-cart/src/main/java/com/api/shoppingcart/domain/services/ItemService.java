package com.api.shoppingcart.domain.services;

import com.api.shoppingcart.domain.dto.ItemDto;
import com.api.shoppingcart.domain.entities.Item;
import com.api.shoppingcart.infrastructure.mongodb.ItemRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    ModelMapper mapper = new ModelMapper();

    public ItemService(){
        this.mapper.getConfiguration().setFieldMatchingEnabled(true)
            .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    public List<ItemDto> getAll(){
        List<Item> itemsListEntity = itemRepository.findAll();
        List<ItemDto> itemsListDto = itemsListEntity
                .stream()
                .map(itemEntity -> this.mapper.map(itemEntity, ItemDto.class))
                .collect(Collectors.toList());
        return itemsListDto;
    }
}
