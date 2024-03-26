package com.maksym.inventoryservice.service;

import com.maksym.inventoryservice.dto.InventoryRequest;
import com.maksym.inventoryservice.dto.InventoryResponse;
import com.maksym.inventoryservice.dtoMapper.InventoryRequestMapper;
import com.maksym.inventoryservice.dtoMapper.InventoryResponseMapper;
import com.maksym.inventoryservice.exception.EntityNotFoundException;
import com.maksym.inventoryservice.feignClient.ProductService;
import com.maksym.inventoryservice.model.Inventory;
import com.maksym.inventoryservice.repository.InventoryRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;
    private final ProductService productService;
    int attempt = 0;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, ProductService productService) {
        this.inventoryRepository = inventoryRepository;
        this.productService = productService;
    }

    @Override
    public Boolean existsById(List<String> id) {
        return inventoryRepository.existsAllByIdIn(id);
    }

    @Override
    @CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    public InventoryResponse add(InventoryRequest inventoryRequest) {
        log.info("Inventory add: {}", inventoryRequest);
        //Check if product exists
        Boolean productExists = productService.existsProduct(inventoryRequest.getProductId()).getBody();
        if(productExists==null || !productExists) throw new EntityNotFoundException("Product with id: " + inventoryRequest.getProductId() + " doesn't exist");

        Inventory inventory = InventoryRequestMapper.toInventory(inventoryRequest);
        inventory = inventoryRepository.save(inventory);
        return InventoryResponseMapper.toInventoryResponse(inventory);
    }

    @Override
    public InventoryResponse get(String id) {
        log.info("Inventory get by id: {}", id);
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Inventory with id: "+ id + "is not found"));
        return InventoryResponseMapper.toInventoryResponse(inventory);
    }

    @Override
    public List<InventoryResponse> getAll() {
        log.info("Inventory get all");
        List<Inventory> inventoryList = inventoryRepository.findAll();
        return inventoryList.stream().map(InventoryResponseMapper::toInventoryResponse).toList();
    }

    @Override
    @CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    public InventoryResponse update(String id, InventoryRequest inventoryRequest) {
        log.info("Inventory update by id: {}, InventoryRequest: {}", id, inventoryRequest);
        Inventory inventory = InventoryRequestMapper.toInventory(inventoryRequest);
        inventory.setId(id);

        //Check if product exists
        Boolean productExists = productService.existsProduct(inventoryRequest.getProductId()).getBody();
        if(productExists==null || !productExists) throw new EntityNotFoundException("Product with id: " + inventoryRequest.getProductId() + " doesn't exist");

        inventory = inventoryRepository.save(inventory);
        return InventoryResponseMapper.toInventoryResponse(inventory);
    }

    @Override
    public Boolean delete(String id) {
        log.info("Inventory delete by id: {}", id);
        inventoryRepository.deleteById(id);
        return true;
    }

    public InventoryResponse companyBreakerFallback (Exception e){
        return new InventoryResponse(null, null, 0,0L);
    }
}
