package com.ai.ecommerce.inventoryservice.service;

import com.ai.ecommerce.inventoryservice.entity.InventoryItem;

import java.util.List;

public interface InventoryService {
    InventoryItem addItem(InventoryItem item);
    InventoryItem updateItem(Long id, InventoryItem item);
    void deleteItem(Long id);
    InventoryItem getItemById(Long id);
    List<InventoryItem> getAllItems();
}
