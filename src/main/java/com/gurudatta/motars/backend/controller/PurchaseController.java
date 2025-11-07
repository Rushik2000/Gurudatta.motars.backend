package com.gurudatta.motars.backend.controller;

import com.gurudatta.motars.backend.model.Supplier;
import com.gurudatta.motars.backend.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend access
public class PurchaseController {

    @Autowired
    SupplierService supplierService;

    public PurchaseController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/supplier/{csid}")
    public ResponseEntity<Supplier> getSupplier(@PathVariable String csid) {
        return ResponseEntity.ok(supplierService.getSupplierById(csid));
    }

    @PostMapping("/supplier")
    public ResponseEntity<Supplier> addSupplier(@RequestBody Supplier supplier) {
        supplier = supplierService.addSupplier(supplier);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(supplier);
    }

    @GetMapping("/searchSupplier")
    public ResponseEntity<List<Supplier>> searchSupplier(@RequestParam String name) {
        List<Supplier> matched = supplierService.searchSuppliersByName(name);
        return ResponseEntity.ok(matched);
    }
}
