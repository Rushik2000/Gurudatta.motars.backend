package com.gurudatta.motars.backend.controller;

import com.gurudatta.motars.backend.model.BillProducts;
import com.gurudatta.motars.backend.service.BillProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BillProductsController {
    @Autowired
    BillProductsService billProductsService;

    public BillProductsController(BillProductsService billProductsService) {
        this.billProductsService = billProductsService;
    }

    @PostMapping("/billProduct")
    public ResponseEntity<BillProducts> addBillProduct(@RequestBody BillProducts billProducts) {
        BillProducts billProduct = billProductsService.addBillProduct(billProducts);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(billProduct);
    }

    @GetMapping("/billProduct/{id}")
    public ResponseEntity<BillProducts> getBillProduct(@PathVariable String bpid) {
        BillProducts billProduct = billProductsService.getBillProduct(bpid);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(billProduct);
    }

    @GetMapping("/bills/{csid}")
    public ResponseEntity<List<BillProducts>> getCustomerAllBills(@PathVariable String csid) {
        List<BillProducts> allBills = billProductsService.getCustomerAllBills(csid);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allBills);
    }
}
