package com.rpg.dummycommerce.stores.application.controller;

import com.rpg.dummycommerce.stores.application.service.StoreService;
import com.rpg.dummycommerce.stores.domain.dto.StoreDto;
import com.rpg.dummycommerce.stores.domain.model.StoreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class StoreController {

    @Autowired
    StoreService storeService;

    @PostMapping("/stores")
    public ResponseEntity<StoreModel> saveStore(@RequestBody StoreDto storeDto) {
        return storeService.saveStore(storeDto);
    }

    @GetMapping("/stores/{id}")
    public ResponseEntity<Object> getStore(@PathVariable(value="id") UUID id) {
        return storeService.getStore(id);
    }

    @PutMapping("/stores/{id}")
    public ResponseEntity<Object> updateStore(@RequestBody StoreDto storeDto, @PathVariable(value="id") UUID id) {
        return storeService.updateStore(storeDto, id);
    }

    @DeleteMapping("/stores/{id}")
    public ResponseEntity<Object> deleteStore(@PathVariable(value = "id") UUID id) {
        return storeService.deleteStore(id);
    }
}