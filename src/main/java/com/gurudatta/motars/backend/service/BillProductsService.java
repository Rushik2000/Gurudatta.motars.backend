package com.gurudatta.motars.backend.service;

import com.gurudatta.motars.backend.model.BillProducts;

public interface BillProductsService {
    BillProducts addBillProduct(BillProducts billProducts);

    BillProducts getBillProduct(String bpid);
}
