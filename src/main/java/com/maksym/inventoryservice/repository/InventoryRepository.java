package com.maksym.inventoryservice.repository;

import com.maksym.inventoryservice.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
    Boolean existsAllByIdIn(List<String> id);
}
