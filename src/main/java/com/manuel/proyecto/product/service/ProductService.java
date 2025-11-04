package com.manuel.proyecto.product.service;

import com.manuel.proyecto.product.exceptions.InvalidProductException;
import com.manuel.proyecto.product.interfaces.ProductRepository;
import com.manuel.proyecto.product.model.Product;

import java.util.List;
import java.util.Optional;

public class ProductService {
    private final   ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    public  List<Product> getAllProducts() throws InvalidProductException {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
}
