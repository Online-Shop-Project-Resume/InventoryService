package com.maksym.inventoryservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryRequest {
    @NotBlank
    private String skuCode;
    @NotNull
    private Long productId;
    @Positive
    private Integer quantity;
}
