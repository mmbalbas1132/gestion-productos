package com.manuel.proyecto.product.interfaces;

import com.manuel.proyecto.product.exceptions.InvalidProductException;
import com.manuel.proyecto.product.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll() throws InvalidProductException;
    Optional<Product> findById(Long id);
    void save(Product product);
    void deleteById(Long id);
    void update(Optional<Product> product);
    boolean existsById(Long id);
}
