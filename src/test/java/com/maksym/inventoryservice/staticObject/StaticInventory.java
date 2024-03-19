package com.maksym.inventoryservice.staticObject;

import com.maksym.inventoryservice.dto.InventoryRequest;
import com.maksym.inventoryservice.dto.InventoryResponse;
import com.maksym.inventoryservice.model.Inventory;

public class StaticInventory {
    public static Inventory inventory1(){
        return new Inventory("1", "SKU001", 1L, 10);
    }
    public static Inventory inventory2(){
        return new Inventory("2", "SKU002", 2L, 20);
    }

    public static InventoryRequest inventoryRequest1(){
        InventoryRequest inventoryRequest = new InventoryRequest();
        inventoryRequest.setProductId(1L);
        inventoryRequest.setQuantity(10);
        inventoryRequest.setSkuCode("SKU001");
        return inventoryRequest;
    }

    public static InventoryResponse inventoryResponse1(){
        InventoryResponse inventoryResponse = new InventoryResponse();
        inventoryResponse.setId("1");
        inventoryResponse.setQuantity(10);
        inventoryResponse.setProductId(1L);
        inventoryResponse.setSkuCode("SKU001");
        return inventoryResponse;
    }
}
