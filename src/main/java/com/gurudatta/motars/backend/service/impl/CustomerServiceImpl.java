package com.gurudatta.motars.backend.service.impl;

import com.gurudatta.motars.backend.model.Customer;
import com.gurudatta.motars.backend.repository.CustomerRepository;
import com.gurudatta.motars.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer getCustomerById(String csid) {
        if (csid != null) {
            return customerRepository.findById(csid).orElseThrow();
        }
        return null;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        if (customer.getCsid() == null || customer.getCsid().equals("")) {
            customer.setCsid(UUID.randomUUID().toString());
        }
        List<String> previousBillsIdList = customer.getPreviousBills();
        if (previousBillsIdList.isEmpty()) {
            List<String> billIdList = new ArrayList<>();
            billIdList.add(customer.getBillProductId());
            customer.setPreviousBills(billIdList);
        } else {
            previousBillsIdList.add(customer.getBillProductId());
        }
        customer = customerRepository.save(customer);
        return customer;
    }

    @Override
    public List<Customer> searchCustomersByName(String name) {
        List<Customer> customerList = null;
        if (!name.isEmpty()) {
            customerList = customerRepository.findByNameContainingIgnoreCase(name);
        }
        return customerList;
    }
}
