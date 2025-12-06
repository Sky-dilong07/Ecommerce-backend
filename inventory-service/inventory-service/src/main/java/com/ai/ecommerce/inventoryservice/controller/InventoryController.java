package com.ai.ecommerce.inventoryservice.controller;

import com.ai.ecommerce.inventoryservice.entity.InventoryItem;
import com.ai.ecommerce.inventoryservice.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<InventoryItem> getAllItems() {
        return inventoryService.getAllItems();
    }

    @GetMapping("/{id}")
    public InventoryItem getItemById(@PathVariable Long id) {
        return inventoryService.getItemById(id);
    }

    @PostMapping
    public InventoryItem addItem(@RequestBody InventoryItem item) {
        return inventoryService.addItem(item);
    }

    @PutMapping("/{id}")
    public InventoryItem updateItem(@PathVariable Long id, @RequestBody InventoryItem item) {
        return inventoryService.updateItem(id, item);
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable Long id) {
        inventoryService.deleteItem(id);
        return "Inventory item deleted successfully!";
    }
}
