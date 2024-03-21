package com.maksym.inventoryservice.service;

import com.maksym.inventoryservice.dto.InventoryRequest;
import com.maksym.inventoryservice.dto.InventoryResponse;
import com.maksym.inventoryservice.dtoMapper.InventoryRequestMapper;
import com.maksym.inventoryservice.dtoMapper.InventoryResponseMapper;
import com.maksym.inventoryservice.exception.EntityNotFoundException;
import com.maksym.inventoryservice.model.Inventory;
import com.maksym.inventoryservice.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Boolean existsById(List<String> id) {
        return inventoryRepository.existsAllByIdIn(id);
    }

    @Override
    public InventoryResponse add(InventoryRequest inventoryRequest) {
        log.info("Inventory add: {}", inventoryRequest);
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
    public InventoryResponse update(String id, InventoryRequest inventoryRequest) {
        log.info("Inventory update by id: {}, InventoryRequest: {}", id, inventoryRequest);
        Inventory inventory = InventoryRequestMapper.toInventory(inventoryRequest);
        inventory.setId(id);

        inventory = inventoryRepository.save(inventory);
        return InventoryResponseMapper.toInventoryResponse(inventory);
    }

    @Override
    public Boolean delete(String id) {
        log.info("Inventory delete by id: {}", id);
        inventoryRepository.deleteById(id);
        return true;
    }
}
