package com.maksym.inventoryservice.service;

import com.maksym.inventoryservice.dto.InventoryRequest;
import com.maksym.inventoryservice.dto.InventoryResponse;
import com.maksym.inventoryservice.model.Inventory;
import com.maksym.inventoryservice.repository.InventoryRepository;
import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class InventoryServiceTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    @Test
    public void testExistsById() {
        // Given
        List<String> ids = Arrays.asList("id1", "id2");
        when(inventoryRepository.existsAllByIdIn(ids)).thenReturn(true);

        // When
        boolean result = inventoryService.existsById(ids);

        // Then
        assertTrue(result);
    }

    @Test
    public void testAdd() {
        // Given
        InventoryRequest request = new InventoryRequest(); // Create a valid request
        Inventory inventory = new Inventory(); // Create a valid inventory
        when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);

        // When
        InventoryResponse response = inventoryService.add(request);

        // Then
        assertNotNull(response);
        // Add more assertions based on the expected behavior
    }

    @Test
    public void testGet() {
        // Given
        String id = "id1";
        Inventory inventory = new Inventory(); // Create a valid inventory
        when(inventoryRepository.findById(id)).thenReturn(Optional.of(inventory));

        // When
        InventoryResponse response = inventoryService.get(id);

        // Then
        assertNotNull(response);
        // Add more assertions based on the expected behavior
    }

    @Test
    public void testGetAll() {
        // Given
        List<Inventory> inventoryList = Arrays.asList(new Inventory(), new Inventory()); // Create a list of inventories
        when(inventoryRepository.findAll()).thenReturn(inventoryList);

        // When
        List<InventoryResponse> responses = inventoryService.getAll();

        // Then
        assertEquals(inventoryList.size(), responses.size());
        // Add more assertions based on the expected behavior
    }

    @Test
    public void testUpdate() {
        // Given
        String id = "id1";
        InventoryRequest request = new InventoryRequest(); // Create a valid request
        Inventory inventory = new Inventory(); // Create a valid inventory
        inventory.setId(id);
        when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);

        // When
        InventoryResponse response = inventoryService.update(id, request);

        // Then
        assertNotNull(response);
        // Add more assertions based on the expected behavior
    }

    @Test
    public void testDelete() {
        // Given
        String id = "id1";

        // When
        boolean result = inventoryService.delete(id);

        // Then
        assertTrue(result);
        // Add more assertions based on the expected behavior
    }
}

