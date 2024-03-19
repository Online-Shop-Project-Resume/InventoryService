package com.maksym.inventoryservice.repository;

import com.maksym.inventoryservice.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
    public Boolean existsAllByIdIn(List<String> id);
}
