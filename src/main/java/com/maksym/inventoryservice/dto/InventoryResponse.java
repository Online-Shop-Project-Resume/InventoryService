package com.maksym.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryResponse{
    private String id;
    private String skuCode;
    private Integer quantity;
    private Long productId;
}
