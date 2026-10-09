package com.ims.backend.service.impl;

import com.ims.backend.exception.ItemNotFoundException;
import com.ims.backend.model.Item;
import com.ims.backend.repository.ItemRepository;
import com.ims.backend.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    private static final Logger transactionLogger = LoggerFactory.getLogger("ims-transaction-logger");


    @Override
    public Page<Item> getAllItems(int page, int size) {

        return itemRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Item createItem(Item item, String username) {
        Item saveItem = itemRepository.save(item);
        transactionLogger.info("TRANSACTION: User '{}' CREATED item '{}' (ID: {}, Qty: {}, Price: {})",
                username, saveItem.getName(), saveItem.getId(), saveItem.getQuantity(), saveItem.getPrice());
        return saveItem;
    }

    @Override
    public Item updateItem(Long id, Item item, String username) {
        Item existingItem = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item with ID " + id + " not found for update"));

        existingItem.setName(item.getName());
        existingItem.setQuantity(item.getQuantity());
        existingItem.setPrice(item.getPrice());

        Item updatedItem = itemRepository.save(existingItem);
        transactionLogger.info("TRANSACTION: User '{}' UPDATED item '{}' (ID: {})",
                username, updatedItem.getName(), updatedItem.getId());
        return updatedItem;
    }

    @Override
    public void deleteItem(Long id, String username) {

        Item foundItem = itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Item with ID " + id + " not found for delete"));

        itemRepository.deleteById(id);
        transactionLogger.info("TRANSACTION: User '{}' DELETED item ID: {}", username, id);
    }
}
