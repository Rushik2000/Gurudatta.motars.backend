package com.gurudatta.motars.backend.service;

import com.gurudatta.motars.backend.dto.ProductDTO;
import com.gurudatta.motars.backend.model.Product;
import com.gurudatta.motars.backend.model.Supplier;

import java.util.List;

public interface ProductService {
    Product getProductById(String pid);
    List<Product> addProduct(List<ProductDTO> products);
    Product updateProduct(Product product);
    List<Product> searchProductsByName(String name);
    List<Product> removeProducts(List<Product> products);
}
