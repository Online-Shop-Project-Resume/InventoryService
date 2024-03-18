package com.maksym.inventoryservice.dtoMapper;

import com.maksym.inventoryservice.dto.InventoryResponse;
import com.maksym.inventoryservice.model.Inventory;

public class InventoryResponseMapper {
    public static Inventory toInventory(InventoryResponse inventoryResponse){
        Inventory inventory = new Inventory();
        inventory.setId(inventoryResponse.getId());
        inventory.setQuantity(inventoryResponse.getQuantity());
        inventory.setSkuCode(inventoryResponse.getSkuCode());
        inventory.setProductId(inventoryResponse.getProductId());
        return inventory;
    }
    public static InventoryResponse toInventoryResponse(Inventory inventory){
        InventoryResponse inventoryResponse = new InventoryResponse();
        inventoryResponse.setId(inventory.getId());
        inventoryResponse.setProductId(inventory.getProductId());
        inventoryResponse.setQuantity(inventory.getQuantity());
        inventoryResponse.setSkuCode(inventory.getSkuCode());
        return inventoryResponse;
    }
}
