package com.maksym.inventoryservice.controller;

import com.maksym.inventoryservice.dto.InventoryRequest;
import com.maksym.inventoryservice.dto.InventoryResponse;
import com.maksym.inventoryservice.model.Inventory;
import com.maksym.inventoryservice.service.InventoryService;
import com.maksym.inventoryservice.service.InventoryServiceImpl;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryServiceImpl inventoryService;

    public InventoryController(InventoryServiceImpl inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inStock")
    public ResponseEntity<Object> isInStock(@RequestParam("id") List<String> id){
        return new ResponseEntity<>(inventoryService.existsById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid InventoryRequest inventoryRequest){
        return new ResponseEntity<>(inventoryService.add(inventoryRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") String id){
        return new ResponseEntity<>(inventoryService.get(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        return new ResponseEntity<>(inventoryService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id){
        return new ResponseEntity<>(inventoryService.delete(id), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody @Valid InventoryRequest request){
        return new ResponseEntity<>(inventoryService.update(id, request), HttpStatus.CREATED);
    }
}
