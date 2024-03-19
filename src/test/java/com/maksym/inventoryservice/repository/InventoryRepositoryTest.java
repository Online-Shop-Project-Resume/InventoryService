package com.maksym.inventoryservice.repository;

import com.maksym.inventoryservice.model.Inventory;
import com.maksym.inventoryservice.staticObject.StaticInventory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@TestPropertySource(properties = {
        "spring.data.mongodb.host=localhost",
        "spring.data.mongodb.port=27017",
        "spring.data.mongodb.database=test"
})
public class InventoryRepositoryTest {
//    TODO
//    @Autowired
//    private InventoryRepository inventoryRepository;
//
//    @Test
//    public void testExistsAllByIdIn_ExistingIds_ReturnsTrue() {
//        // Prepare test data
//        Inventory inventory1 = StaticInventory.inventory1();
//        Inventory inventory2 = StaticInventory.inventory2();
//        inventoryRepository.saveAll(Arrays.asList(inventory1, inventory2));
//
//        // Test existsAllByIdIn method
//        List<String> ids = Arrays.asList("1", "2");
//        boolean exists = inventoryRepository.existsAllByIdIn(ids);
//
//        // Verify the result
//        assertThat(exists).isTrue();
//    }

}
