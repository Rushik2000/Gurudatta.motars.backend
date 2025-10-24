package com.gurudatta.motars.backend.repository;

import com.gurudatta.motars.backend.model.BillProducts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillProductsRepository extends MongoRepository<BillProducts, String> {
}
