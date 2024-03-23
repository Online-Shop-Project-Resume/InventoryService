package com.maksym.inventoryservice.service;

import com.maksym.inventoryservice.dto.InventoryRequest;
import com.maksym.inventoryservice.dto.InventoryResponse;
import com.maksym.inventoryservice.exception.EntityNotFoundException;
import com.maksym.inventoryservice.feignClient.ProductService;
import com.maksym.inventoryservice.model.Inventory;
import com.maksym.inventoryservice.repository.InventoryRepository;
import com.maksym.inventoryservice.staticObject.StaticInventory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class InventoryServiceTest {

    private final String id1 = "1";
    private final String id2 = "2";
    @Mock
    private InventoryRepository inventoryRepository;
    @Mock
    private ProductService productService;
    @InjectMocks
    private InventoryServiceImpl inventoryService;

    @Test
    public void testExistsById() {
        // Given
        List<String> ids = Arrays.asList(id1, id2);
        when(inventoryRepository.existsAllByIdIn(ids)).thenReturn(true);

        // When
        boolean result = inventoryService.existsById(ids);

        // Then
        assertTrue(result);
    }

    @Test
    public void testAdd() {
        InventoryRequest request = StaticInventory.inventoryRequest1();
        Inventory inventory = StaticInventory.inventory1();

        // Mocking ProductService.existsProduct response
        when(productService.existsProduct(anyLong())).thenReturn(new ResponseEntity<>(true, HttpStatus.OK));

        // Mocking InventoryRepository.save response
        when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);

        // Call the method
        InventoryResponse response = inventoryService.add(request);

        // Verify interactions
        verify(productService).existsProduct(anyLong());
        verify(inventoryRepository).save(any(Inventory.class));

        // Assert the response
        assertNotNull(response);
        // Add more assertions based on expected behavior
    }

    @Test
    public void testUpdate() {
        InventoryRequest request = StaticInventory.inventoryRequest1();
        Inventory inventory = StaticInventory.inventory1();

        // Mocking ProductService.existsProduct response
        when(productService.existsProduct(anyLong())).thenReturn(new ResponseEntity<>(true, HttpStatus.OK));

        // Mocking InventoryRepository.save response
        when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);

        // Call the method
        InventoryResponse response = inventoryService.update(id1, request);

        // Verify interactions
        verify(productService).existsProduct(anyLong());
        verify(inventoryRepository).save(any(Inventory.class));

        // Assert the response
        assertNotNull(response);
        // Add more assertions based on expected behavior
    }

    @Test
    public void testAdd_ProductNotExists() {
        InventoryRequest request = StaticInventory.inventoryRequest1();

        // Mocking ProductService.existsProduct response for product not exists scenario
        when(productService.existsProduct(anyLong())).thenReturn(new ResponseEntity<>(false, HttpStatus.OK));

        // Call the method and assert for EntityNotFoundException
        assertThrows(EntityNotFoundException.class, () -> inventoryService.add(request));
    }

    @Test
    public void testUpdate_ProductNotExists() {
        InventoryRequest request = StaticInventory.inventoryRequest1();

        // Mocking ProductService.existsProduct response for product not exists scenario
        when(productService.existsProduct(anyLong())).thenReturn(new ResponseEntity<>(false, HttpStatus.OK));

        // Call the method and assert for EntityNotFoundException
        assertThrows(EntityNotFoundException.class, () -> inventoryService.update(id1, request));
    }

    @Test
    public void testGet() {
        Inventory inventory = StaticInventory.inventory1(); // Create a valid inventory
        when(inventoryRepository.findById(id1)).thenReturn(Optional.of(inventory));

        // When
        InventoryResponse response = inventoryService.get(id1);

        // Then
        assertNotNull(response);
    }

    @Test
    public void testGetAll() {
        // Given
        List<Inventory> inventoryList = Arrays.asList(StaticInventory.inventory1(), StaticInventory.inventory2()); // Create a list of inventories
        when(inventoryRepository.findAll()).thenReturn(inventoryList);

        // When
        List<InventoryResponse> responses = inventoryService.getAll();

        // Then
        assertEquals(inventoryList.size(), responses.size());
        // Add more assertions based on the expected behavior
    }

    @Test
    public void testDelete() {
        // When
        boolean result = inventoryService.delete(id1);

        // Then
        assertTrue(result);
        // Add more assertions based on the expected behavior
    }
}

