package com.manuel.proyecto.product.controller;

import com.manuel.proyecto.product.exceptions.InvalidProductException;
import com.manuel.proyecto.product.model.Product;
import com.manuel.proyecto.product.service.ProductService;
import com.manuel.proyecto.product.utils.Validates;

import java.util.List;
import java.util.Optional;

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public void addProduct(Product product) throws InvalidProductException {
        Validates.ValidarObjeto(product, "El producto no puede ser nulo.");
        productService.saveProduct(product);
    }

    public void removeProduct(Long id) throws InvalidProductException {
        Validates.validarNumero(id, "El ID del producto no puede ser nulo.");
        productService.deleteProductById(id);
    }

    public List<Product> getAllProducts() throws InvalidProductException {
        return productService.getAllProducts();
    }

    public Optional<Product> getProductById(Long id) throws InvalidProductException {
        Validates.validarNumero(id, "El ID del producto no puede ser nulo.");
        return productService.getProductById(id);
    }

    public void updateProduct(Product product) throws InvalidProductException {
        Validates.ValidarObjeto(product, "El producto no puede ser nulo.");
        productService.updateProduct(product);
    }
}
