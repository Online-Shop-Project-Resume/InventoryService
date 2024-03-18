package com.maksym.inventoryservice.dtoMapper;

import com.maksym.inventoryservice.dto.InventoryRequest;
import com.maksym.inventoryservice.model.Inventory;

public class InventoryRequestMapper {
    public static Inventory toInventory(InventoryRequest inventoryRequest){
        Inventory inventory = new Inventory();
        inventory.setQuantity(inventoryRequest.getQuantity());
        inventory.setSkuCode(inventoryRequest.getSkuCode());
        inventory.setProductId(inventoryRequest.getProductId());
        return inventory;
    }
    public static InventoryRequest toInventoryRequest(Inventory inventory){
        InventoryRequest inventoryRequest = new InventoryRequest();
        inventoryRequest.setQuantity(inventory.getQuantity());
        inventoryRequest.setSkuCode(inventory.getSkuCode());
        inventoryRequest.setProductId(inventory.getProductId());
        return inventoryRequest;
    }
}
