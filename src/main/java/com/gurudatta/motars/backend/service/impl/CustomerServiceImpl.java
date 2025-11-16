package com.gurudatta.motars.backend.service.impl;

import com.gurudatta.motars.backend.model.Customer;
import com.gurudatta.motars.backend.repository.CustomerRepository;
import com.gurudatta.motars.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        Customer dbCustomerData = null;
        if (customer.getCsid() != null) {
            dbCustomerData = customerRepository.findById(customer.getCsid()).orElse(null);
        } else {
            dbCustomerData = customerRepository.findByName(customer.getName());
        }

        if (customer.getCsid() == null || customer.getCsid().equals("")) {
            customer.setCsid(UUID.randomUUID().toString());
        }

        if (customer.getBillProductId() != null) {
            if (dbCustomerData == null) {
                List<String> billIdList = new ArrayList<>();
                billIdList.add(customer.getBillProductId());
                customer.setPreviousBills(billIdList);
            } else {
                customer.setPreviousBills(dbCustomerData.getPreviousBills());
                if (customer.getBillProductId() != null) {
                    String incomingBillId = customer.getBillProductId();
                    List<String> prevBillIdList = customer.getPreviousBills();
                    Optional<String> billIdPresent = prevBillIdList
                            .stream().filter(eachBillId ->
                                    eachBillId.equals(incomingBillId)).findFirst();
                    if (billIdPresent.isEmpty()) customer.getPreviousBills().add(customer.getBillProductId());
                }
            }
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

    @Override
    public Customer searchCustomerByPhone(String phone) {
        Customer customer = null;
        if (phone != null) {
            customer = customerRepository.findByPhone(phone);
        }
        return customer;
    }
}
