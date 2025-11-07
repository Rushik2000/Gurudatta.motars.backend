package com.gurudatta.motars.backend.service;

import com.gurudatta.motars.backend.model.BillProducts;

import java.util.List;

public interface BillProductsService {
    BillProducts addBillProduct(BillProducts billProducts);

    BillProducts getBillProduct(String bpid);

    List<BillProducts> getCustomerAllBills(String csid);
    List<BillProducts> getSupplierAllBills(String sid);
}
