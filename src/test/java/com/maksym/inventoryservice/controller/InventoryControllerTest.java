package com.maksym.inventoryservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maksym.inventoryservice.dto.InventoryRequest;
import com.maksym.inventoryservice.dto.InventoryResponse;
import com.maksym.inventoryservice.service.InventoryServiceImpl;
import com.maksym.inventoryservice.staticObject.StaticInventory;
import jakarta.validation.ConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(InventoryController.class)
public class InventoryControllerTest {

    private final String id1 = "1";
    private final String id2 = "2";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryServiceImpl inventoryService;

    @Test
    public void testIsInStock() throws Exception {
        // Given
        List<String> ids = Arrays.asList(id1, id2);
        when(inventoryService.existsById(ids)).thenReturn(true);

        // When
        mockMvc.perform(get("/api/inventory/inStock").param("id", id1, id2))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testCreateInventory() throws Exception {
        // Given
        InventoryRequest request = StaticInventory.inventoryRequest1(); // Create a valid request
        InventoryResponse response = new InventoryResponse(); // Create a valid response
        when(inventoryService.add(any(InventoryRequest.class))).thenReturn(response);

        // When
        mockMvc.perform(post("/api/inventory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGet() throws Exception {
        InventoryResponse response = StaticInventory.inventoryResponse1(); // Create a valid response
        when(inventoryService.get(id1)).thenReturn(response);

        // When
        mockMvc.perform(get("/api/inventory/{id}", id1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id1)); // Assuming id is part of the response JSON
    }

    @Test
    public void testGetAll() throws Exception {
        // Given
        List<InventoryResponse> responses = Arrays.asList(new InventoryResponse(), new InventoryResponse()); // Create a list of responses
        when(inventoryService.getAll()).thenReturn(responses);

        // When
        mockMvc.perform(get("/api/inventory"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))); // Assuming the response is a JSON array with two items
    }

    @Test
    public void testDelete() throws Exception {
        // When
        mockMvc.perform(delete("/api/inventory/{id}", id1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testUpdate() throws Exception {
        // Given
        InventoryRequest request = StaticInventory.inventoryRequest1(); // Create a valid request
        InventoryResponse response = StaticInventory.inventoryResponse1(); // Create a valid response
        when(inventoryService.update(id1, request)).thenReturn(response);

        // When
        mockMvc.perform(put("/api/inventory/{id}", id1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testHandleConstraintViolationException() throws Exception {
        // Given
        when(inventoryService.add(any(InventoryRequest.class))).thenThrow(new ConstraintViolationException(new HashSet<>()));

        // When
        mockMvc.perform(post("/api/inventory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")) // Sending an empty JSON request to trigger the validation error
                .andExpect(status().isBadRequest());
    }
}
