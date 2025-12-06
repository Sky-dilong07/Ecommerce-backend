package com.ai.ecommerce.inventoryservice.service;

import com.ai.ecommerce.inventoryservice.entity.InventoryItem;
import com.ai.ecommerce.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public InventoryItem addItem(InventoryItem item){
        item.setLastUpdated(LocalDateTime.now());
        return inventoryRepository.save(item);
    }

    @Override
    public InventoryItem updateItem(Long id, InventoryItem item) {
        InventoryItem existingItem = getItemById(id);
        existingItem.setProductName(item.getProductName());
        existingItem.setQuantity(item.getQuantity());
        existingItem.setLocation(item.getLocation());
        existingItem.setLastUpdated(LocalDateTime.now());
        return inventoryRepository.save(existingItem);
    }

    @Override
    public void deleteItem(Long id) {
        inventoryRepository.deleteById(id);
    }

    @Override
    public InventoryItem getItemById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory item not found with id: " + id));
    }

    @Override
    public List<InventoryItem> getAllItems() {
        return inventoryRepository.findAll();
    }
}
