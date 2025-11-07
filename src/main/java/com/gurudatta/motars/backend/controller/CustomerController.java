package com.gurudatta.motars.backend.controller;

import com.gurudatta.motars.backend.model.Customer;
import com.gurudatta.motars.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend access
public class CustomerController {
    @Autowired
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer/{csid}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String csid) {
        return ResponseEntity.ok(customerService.getCustomerById(csid));
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        customer = customerService.addCustomer(customer);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customer);
    }

    @GetMapping("/searchCustomer")
    public ResponseEntity<List<Customer>> searchCustomers(@RequestParam String name) {
        List<Customer> matched = customerService.searchCustomersByName(name);
        return ResponseEntity.ok(matched);
    }


}