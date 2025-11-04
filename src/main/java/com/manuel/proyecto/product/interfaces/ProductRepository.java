package com.manuel.proyecto.product.interfaces;

import com.manuel.proyecto.product.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();
    Optional<Product> findById(long id);
    void save(Product product);
    void deleteById(long id);
    void update(Optional<Product> product);
    boolean existsById(long id);
}
