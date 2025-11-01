package com.gurudatta.motars.backend.service.impl;

import com.gurudatta.motars.backend.model.BillProducts;
import com.gurudatta.motars.backend.model.Customer;
import com.gurudatta.motars.backend.model.Product;
import com.gurudatta.motars.backend.repository.BillProductsRepository;
import com.gurudatta.motars.backend.repository.CustomerRepository;
import com.gurudatta.motars.backend.service.BillProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class BillProductsServiceImpl implements BillProductsService {
    @Autowired
    BillProductsRepository billProductsRepository;

    @Autowired
    CustomerServiceImpl customerService;

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

    @Override
    public List<BillProducts> getCustomerAllBills(String csid) {
        Customer customer = customerService.getCustomerById(csid);
        if (customer.getPreviousBills() != null && !customer.getPreviousBills().isEmpty()) {
            List<BillProducts> billList = new ArrayList<>();
            List<String> previousBillIds = customer.getPreviousBills();
            previousBillIds.forEach(eachBid -> {
                BillProducts bill = billProductsRepository.findById(eachBid).orElseThrow();
                billList.add(bill);
            });
            return billList;
        }
        return null;
    }
}
