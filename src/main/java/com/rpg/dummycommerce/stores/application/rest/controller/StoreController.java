package com.rpg.dummycommerce.stores.application.rest.controller;

import com.rpg.dummycommerce.stores.application.service.StoreService;
import com.rpg.dummycommerce.stores.application.global.exception.GlobalExceptionHandler;
import com.rpg.dummycommerce.stores.domain.dto.StoreDto;
import com.rpg.dummycommerce.stores.domain.model.StoreModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.UUID;

@RestController
public class StoreController {

    @Autowired
    StoreService storeService;

    @Autowired
    GlobalExceptionHandler validationResponse;

    @PostMapping("/stores")
    public ResponseEntity<StoreModel> saveStore(@RequestBody StoreDto storeDto) {
        var storeModel = storeService.saveStore(storeDto);
        return ResponseEntity.status(HttpStatus.OK).body(storeModel);
    }

    @GetMapping("/stores/{id}")
    public ResponseEntity<Object> getStore(@PathVariable(value="id") UUID id) {
        var storeModel = storeService.getStoreById(id);
        return ResponseEntity.status(HttpStatus.OK).body(storeModel);
    }

    @PutMapping("/stores/{id}")
    public ResponseEntity<Object> updateStore(@RequestBody StoreDto storeDto, @PathVariable(value="id") UUID id) {
        var storeModel = storeService.updateStore(storeDto, id);
        if (storeModel == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Store not found");
        return ResponseEntity.status(HttpStatus.OK).body(storeModel);
    }

    @DeleteMapping("/stores/{id}")
    public ResponseEntity<Object> deleteStore(@PathVariable(value = "id") UUID id) {
        storeService.deleteStore(id);
        return ResponseEntity.status(HttpStatus.OK).body("Store deleted");
    }
}