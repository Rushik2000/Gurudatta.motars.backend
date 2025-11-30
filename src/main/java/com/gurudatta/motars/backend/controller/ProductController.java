package com.gurudatta.motars.backend.controller;

import com.gurudatta.motars.backend.dto.ProductDTO;
import com.gurudatta.motars.backend.model.Product;
import com.gurudatta.motars.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{pid}")
    public ResponseEntity<Product> getProduct(@PathVariable String pid) {
        return ResponseEntity.ok(productService.getProductById(pid));
    }

    @PostMapping("/product")
    public ResponseEntity<List<Product>> addProduct(@RequestBody List<ProductDTO> productsDto) {
        List<Product> products = productService.addProduct(productsDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(products);
    }

    @PutMapping("/product")
    public ResponseEntity<Product> setProduct(@RequestBody Product product) {
        product = productService.updateProduct(product);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(product);
    }

    @DeleteMapping("/product")
    public ResponseEntity<List<ProductDTO>> removeProduct(@RequestBody List<ProductDTO> productsDto) {
        productsDto = productService.removeProducts(productsDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productsDto);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productList);
    }

    @GetMapping("/searchProduct")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String name) {
        List<Product> matched = productService.searchProductsByName(name);
        return ResponseEntity.ok(matched);
    }
}
