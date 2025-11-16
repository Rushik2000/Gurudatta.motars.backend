package com.gurudatta.motars.backend.service;

import com.gurudatta.motars.backend.model.Supplier;
import java.util.List;

public interface SupplierService {
    Supplier getSupplierById(String csid);

    Supplier addSupplier(Supplier customer);

    List<Supplier> searchSuppliersByName(String name);

    Supplier searchSupplierByPhone(String phone);
}
