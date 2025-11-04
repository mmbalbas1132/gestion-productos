package com.manuel.proyecto.product.service;

import com.manuel.proyecto.product.exceptions.InvalidProductException;
import com.manuel.proyecto.product.exceptions.ProductNotFoundException;
import com.manuel.proyecto.product.interfaces.ProductRepository;
import com.manuel.proyecto.product.model.Product;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() throws InvalidProductException {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public void saveProduct(Product product) throws InvalidProductException {
        if (productRepository.existsById(product.getId())) {
            productRepository.save(product);
            System.out.println("Producto con ID " + product.getId() + " guardado correctamente.");
        } else {
            throw new InvalidProductException("El producto con ID " + product.getId() + " ya existe.");
        }

    }

    public void deleteProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
            System.out.println("Producto con ID " + id + " eliminado correctamente.");
        } else {
            throw new ProductNotFoundException("El producto con ID " + id + " no existe.");
        }
    }

    public  void updateProduct(Product product) throws InvalidProductException {
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        if (optionalProduct.isPresent()) {
            productRepository.update(Optional.of(product));
            System.out.println("Producto con ID " + product.getId() + " actualizado correctamente.");
        } else {
            throw new InvalidProductException("El producto con ID " + product.getId() + " no existe.");
        }