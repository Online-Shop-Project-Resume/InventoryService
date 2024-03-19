package com.maksym.inventoryservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("t_inventory")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Inventory {
    @Id
    private String id;
    @NotBlank
    private String skuCode;
    @NotNull
    private Long productId;
    @Positive
    private Integer quantity;
}
