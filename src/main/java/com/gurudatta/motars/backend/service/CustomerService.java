package com.gurudatta.motars.backend.service;

import com.gurudatta.motars.backend.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomerById(String csid);

    Customer addCustomer(Customer customer);

    List<Customer> searchCustomersByName(String name);
}
