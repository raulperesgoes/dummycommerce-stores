package com.rpg.dummycommerce.stores.application.service;

import com.rpg.dummycommerce.stores.domain.dto.StoreDto;
import com.rpg.dummycommerce.stores.domain.model.StoreModel;
import com.rpg.dummycommerce.stores.domain.repository.StoreRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public ResponseEntity<StoreModel> saveStore(StoreDto storeDto) {
        var storeModel = new StoreModel();
        BeanUtils.copyProperties(storeDto, storeModel);
        storeModel.setDeleted(false);

        return ResponseEntity.status(HttpStatus.CREATED).body(storeRepository.save(storeModel));
    }

    public ResponseEntity<Object> getStore(UUID id) {
        Optional<StoreModel> storeO = storeRepository.findById(id);
        if (storeNotFoundOrDeleted(storeO)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Store not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(storeO.get());
    }

    public ResponseEntity<Object> updateStore(StoreDto storeDto, UUID id) {
        Optional<StoreModel> storeO = storeRepository.findById(id);
        if (storeNotFoundOrDeleted(storeO)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Store not found");
        }
        var storeModel = new StoreModel();
        BeanUtils.copyProperties(storeDto, storeModel);
        return ResponseEntity.status(HttpStatus.OK).body(storeRepository.save(storeModel));
    }

    public ResponseEntity<Object> deleteStore(UUID id) {
        Optional<StoreModel> storeO = storeRepository.findById(id);
        if (storeNotFoundOrDeleted(storeO)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Store not found.");
        }
        var storeModel = new StoreModel();
        BeanUtils.copyProperties(storeO.get(), storeModel);
        storeModel.setDeleted(true);
        storeRepository.save(storeModel);
        return ResponseEntity.status(HttpStatus.OK).body("Store deleted");
    }

    private boolean storeNotFoundOrDeleted(Optional<StoreModel> storeO) {
        return storeO.isEmpty() || storeO.get().isDeleted();
    }
}
