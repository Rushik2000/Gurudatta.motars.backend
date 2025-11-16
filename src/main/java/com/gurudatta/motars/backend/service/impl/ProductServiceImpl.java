package com.gurudatta.motars.backend.service.impl;

import com.gurudatta.motars.backend.dto.ProductDTO;
import com.gurudatta.motars.backend.model.Product;
import com.gurudatta.motars.backend.repository.ProductRepository;
import com.gurudatta.motars.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product getProductById(String pid) {
        if (pid != null) {
            return productRepository.findById(pid).orElseThrow();
        }
        return null;
    }

    @Override
    public List<Product> addProduct(List<ProductDTO> productsDto) {
        // Ignore price coming for the product and let user add the manual price for now
        List<Product> savedProducts = new ArrayList<>();
        for (ProductDTO pDto : productsDto) {
            int purchasedQty = pDto.getQuantity();
            Product dbMatch = productRepository.findByName(pDto.getName());
            if (dbMatch != null) {
                int updatedQuantity = dbMatch.getQuantity() + purchasedQty;
                pDto.setPid(dbMatch.getPid());
                pDto.setQuantity(updatedQuantity);
            }
            Product product = convertDTOToEntity(pDto);
            product = productRepository.save(product);
            product.setPrice(pDto.getPrice());
            product.setQuantity(purchasedQty);
            savedProducts.add(product);
        }
        return savedProducts;
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> removeProducts(List<Product> products) {
        List<Product> updatedProducts = new ArrayList<>();
        for (Product requestedProd : products) {
            productRepository.findById(requestedProd.getPid()).ifPresent(dbProd -> {
                if (dbProd.getName().equals(requestedProd.getName())) {
                    dbProd.setQuantity(dbProd.getQuantity() - requestedProd.getQuantity());
                    updatedProducts.add(dbProd);
                }
            });
        }
        if (!updatedProducts.isEmpty()) {
            productRepository.saveAll(updatedProducts);
        }
        return products;
    }


    @Override
    public List<Product> searchProductsByName(String name) {
        List<Product> productsList = null;
        if (!name.isEmpty()) {
            productsList = productRepository.findByNameContainingIgnoreCase(name);
        }
        return productsList;
    }

    private Product convertDTOToEntity(ProductDTO dto) {
        Product p = new Product();
        p.setPid(dto.getPid() != null ? dto.getPid() : UUID.randomUUID().toString());
        p.setName(dto.getName());
        p.setQuantity(dto.getQuantity());
        return p;
    }

}
