package com.gurudatta.motars.backend.repository;

import com.gurudatta.motars.backend.model.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends MongoRepository<Supplier, String> {
    List<Supplier> findByNameContainingIgnoreCase(String name);
}
