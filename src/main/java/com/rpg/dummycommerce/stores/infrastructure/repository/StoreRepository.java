package com.rpg.dummycommerce.stores.infrastructure.repository;


import com.rpg.dummycommerce.stores.domain.model.StoreModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<StoreModel, UUID> {
}
