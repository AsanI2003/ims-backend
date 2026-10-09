package com.ims.backend.controller;

import com.ims.backend.model.Item;
import com.ims.backend.service.ItemService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "http://localhost:5173")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public Page<Item> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return itemService.getAllItems(page, size);
    }

    @PostMapping
    public Item create(@RequestBody Item item, Authentication authentication) {
        return itemService.createItem(item, authentication.getName());
    }

    @PutMapping("/{id}")
    public Item update(@PathVariable Long id, @RequestBody Item item, Authentication authentication) {
        return itemService.updateItem(id, item, authentication.getName());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, Authentication authentication) {
        itemService.deleteItem(id, authentication.getName());
    }
}
