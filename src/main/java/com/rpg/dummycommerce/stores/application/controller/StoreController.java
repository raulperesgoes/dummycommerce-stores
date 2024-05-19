package com.rpg.dummycommerce.stores.application.controller;

import com.rpg.dummycommerce.stores.domain.dto.StoreDto;
import com.rpg.dummycommerce.stores.domain.model.StoreModel;
import com.rpg.dummycommerce.stores.domain.repository.StoreRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class StoreController {

    @Autowired
    StoreRepository storeRepository;

    @PostMapping("/stores")
    public ResponseEntity<StoreModel> saveStore(@RequestBody StoreDto storeDto) {
        var storeModel = new StoreModel();
        BeanUtils.copyProperties(storeDto, storeModel);
        storeModel.setDeleted(false);
        return ResponseEntity.status(HttpStatus.CREATED).body(storeRepository.save(storeModel));
    }

    @GetMapping("/stores/{id}")
    public ResponseEntity<Object> getStore(@PathVariable(value="id") UUID id) {
        Optional<StoreModel> storeO = storeRepository.findById(id);
        if (storeO.isEmpty() || storeO.get().isDeleted() == true) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Store not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(storeO.get());
    }

    @PutMapping("/stores/{id}")
    public ResponseEntity<Object> updateStore(@RequestBody StoreDto storeDto, @PathVariable(value="id") UUID id) {
        Optional<StoreModel> storeO = storeRepository.findById(id);
        if (storeO.isEmpty() || storeO.get().isDeleted() == true) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Store not found");
        }
        var storeModel = new StoreModel();
        BeanUtils.copyProperties(storeDto, storeModel);
        return ResponseEntity.status(HttpStatus.OK).body(storeRepository.save(storeModel));
    }

    @DeleteMapping("/stores/{id}")
    public ResponseEntity<Object> deleteStore(@PathVariable(value = "id") UUID id) {
        Optional<StoreModel> storeO = storeRepository.findById(id);
        if (storeO.isEmpty() || storeO.get().isDeleted() == true) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Store not found.");
        }
        var storeModel = new StoreModel();
        BeanUtils.copyProperties(storeO.get(), storeModel);
        storeModel.setDeleted(true);
        storeRepository.save(storeModel);
        return ResponseEntity.status(HttpStatus.OK).body("Store deleted");
    }
}