package com.gurudatta.motars.backend.service.impl;

import com.gurudatta.motars.backend.model.Supplier;
import com.gurudatta.motars.backend.repository.SupplierRepository;
import com.gurudatta.motars.backend.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Supplier getSupplierById(String csid) {
        if (csid != null) {
            return supplierRepository.findById(csid).orElseThrow();
        }
        return null;
    }

    @Override
    public Supplier addSupplier(Supplier supplier) {
        Supplier dbSupplierData = null;
        if (supplier.getSid() != null) {
            dbSupplierData = supplierRepository.findById(supplier.getSid()).orElse(null);
        } else {
            dbSupplierData = supplierRepository.findByName(supplier.getName());
        }

        if (supplier.getSid() == null || supplier.getSid().equals("")) {
            supplier.setSid(UUID.randomUUID().toString());
        }

        if (supplier.getBillId() != null) {
            if (dbSupplierData == null) {
                List<String> billIdList = new ArrayList<>();
                billIdList.add(supplier.getBillId());
                supplier.setPreviousBills(billIdList);
            } else {
                supplier.setPreviousBills(dbSupplierData.getPreviousBills());
                if (supplier.getBillId() != null) {
                    String incomingBillId = supplier.getBillId();
                    List<String> prevBillIdList = supplier.getPreviousBills();
                    Optional<String> billIdPresent = prevBillIdList
                            .stream().filter(eachBillId ->
                                    eachBillId.equals(incomingBillId)).findFirst();
                    if (billIdPresent.isEmpty()) supplier.getPreviousBills().add(supplier.getBillId());
                }
            }
        }
        supplier = supplierRepository.save(supplier);
        return supplier;
    }

    @Override
    public List<Supplier> searchSuppliersByName(String name) {
        List<Supplier> supplierList = null;
        if (!name.isEmpty()) {
            supplierList = supplierRepository.findByNameContainingIgnoreCase(name);
        }
        return supplierList;
    }

    @Override
    public Supplier searchSupplierByPhone(String phone) {
        Supplier supplier = null;
        if (phone != null) {
            supplier = supplierRepository.findByPhone(phone);
        }
        return supplier;
    }
}
