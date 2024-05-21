package com.rpg.dummycommerce.stores.application.service;

import com.rpg.dummycommerce.stores.domain.dto.StoreDto;
import com.rpg.dummycommerce.stores.domain.exception.StoreNotFoundException;
import com.rpg.dummycommerce.stores.domain.model.StoreModel;
import com.rpg.dummycommerce.stores.infrastructure.repository.StoreRepository;
import jakarta.persistence.PersistenceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.UUID;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public StoreModel saveStore(StoreDto storeDto) {
        var storeModel = new StoreModel();
        BeanUtils.copyProperties(storeDto, storeModel);
        storeModel.setDeleted(false);
        return storeRepository.save(storeModel);
    }

    public StoreModel getStoreById(UUID id) {
        return getStore(id);
    }

    public StoreModel updateStore(StoreDto storeDto, UUID id) {
        var storeModel = getStore(id);
        BeanUtils.copyProperties(storeDto, storeModel);
        storeRepository.save(storeModel);
        return storeModel;
    }

    public void deleteStore(UUID id) {
        var storeModel = getStore(id);
        storeModel.setDeleted(true);
        storeRepository.save(storeModel);
    }

    private StoreModel getStore(UUID id) {
        return storeRepository.findById(id)
                .filter(store -> !store.isDeleted())
                .orElseThrow(() -> new StoreNotFoundException("Store not found id " + id));
    }
}
