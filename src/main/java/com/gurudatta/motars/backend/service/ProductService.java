package com.gurudatta.motars.backend.service;

import com.gurudatta.motars.backend.dto.ProductDTO;
import com.gurudatta.motars.backend.model.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(String pid);
    List<Product> addProduct(List<ProductDTO> products);
    Product updateProduct(Product product);
    List<Product> searchProductsByName(String name);
    List<ProductDTO> removeProducts(List<ProductDTO> products);

    List<Product> getAllProducts();
}
