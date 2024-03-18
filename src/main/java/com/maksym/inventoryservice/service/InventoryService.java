package com.maksym.inventoryservice.service;

import com.maksym.inventoryservice.dto.InventoryRequest;
import com.maksym.inventoryservice.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {
    public Boolean existsById(List<String> id);
    public InventoryResponse add(InventoryRequest inventoryRequest);
    public InventoryResponse get(String id);
    public List<InventoryResponse> getAll();
    public InventoryResponse update(String id, InventoryRequest inventoryRequest);
    public Boolean delete(String id);
}
