package com.ims.backend.service;

import com.ims.backend.model.Item;
import org.springframework.data.domain.Page;



public interface ItemService {
    Page<Item> getAllItems(int page, int size);
    Item createItem(Item item, String username);
    Item updateItem(Long id, Item item, String username);
    void deleteItem(Long id, String username);
}
