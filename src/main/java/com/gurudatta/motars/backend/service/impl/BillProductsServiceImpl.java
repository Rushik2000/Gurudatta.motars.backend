package com.gurudatta.motars.backend.service.impl;

import com.gurudatta.motars.backend.model.BillProducts;
import com.gurudatta.motars.backend.repository.BillProductsRepository;
import com.gurudatta.motars.backend.service.BillProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BillProductsServiceImpl implements BillProductsService {
    @Autowired
    BillProductsRepository billProductsRepository;

    @Override
    public BillProducts addBillProduct(BillProducts billProducts) {
        if (billProducts.getBpid() == null || billProducts.getBpid().equals(""))
            billProducts.setBpid(UUID.randomUUID().toString());
        return billProductsRepository.save(billProducts);
    }

    @Override
    public BillProducts getBillProduct(String bpid) {
        return billProductsRepository.findById(bpid).orElseThrow();
    }
}
