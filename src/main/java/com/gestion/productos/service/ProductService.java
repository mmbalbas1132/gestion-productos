package com.gestion.productos.service;

import com.gestion.productos.model.Product;
import com.gestion.productos.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Servicio de negocio para gestionar productos.
 * Implementa la lógica de negocio sobre el repositorio de productos.
 */
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository repository;
    
    /**
     * Crea un nuevo producto.
     * 
     * @param product el producto a crear
     * @return el producto creado
     * @throws IllegalArgumentException si los datos del producto son inválidos
     */
    public Product createProduct(Product product) {
        validateProduct(product);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return repository.save(product);
    }
    
    /**
     * Actualiza un producto existente.
     * 
     * @param id el ID del producto a actualizar
     * @param updatedProduct los nuevos datos del producto
     * @return el producto actualizado
     * @throws IllegalArgumentException si el producto no existe o los datos son inválidos
     */
    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProduct = repository.findById(id);
        if (existingProduct.isEmpty()) {
            throw new IllegalArgumentException("Producto con ID " + id + " no encontrado");
        }
        
        validateProduct(updatedProduct);
        updatedProduct.setId(id);
        updatedProduct.setCreatedAt(existingProduct.get().getCreatedAt());
        updatedProduct.setUpdatedAt(LocalDateTime.now());
        
        return repository.save(updatedProduct);
    }
    
    /**
     * Busca un producto por su ID.
     * 
     * @param id el ID del producto
     * @return Optional con el producto si existe
     */
    public Optional<Product> getProductById(Long id) {
        return repository.findById(id);
    }
    
    /**
     * Obtiene todos los productos.
     * 
     * @return lista de todos los productos
     */
    public List<Product> getAllProducts() {
        return repository.findAll();
    }
    
    /**
     * Busca productos por categoría.
     * 
     * @param category la categoría a buscar
     * @return lista de productos de la categoría
     */
    public List<Product> getProductsByCategory(String category) {
        return repository.findByCategory(category);
    }
    
    /**
     * Busca productos por nombre.
     * 
     * @param name el nombre o parte del nombre a buscar
     * @return lista de productos que coinciden
     */
    public List<Product> searchProductsByName(String name) {
        return repository.findByNameContaining(name);
    }
    
    /**
     * Elimina un producto por su ID.
     * 
     * @param id el ID del producto a eliminar
     * @return true si fue eliminado, false si no existía
     */
    public boolean deleteProduct(Long id) {
        return repository.deleteById(id);
    }
    
    /**
     * Incrementa el stock de un producto.
     * 
     * @param id el ID del producto
     * @param quantity la cantidad a agregar
     * @return el producto actualizado
     * @throws IllegalArgumentException si el producto no existe
     */
    public Product increaseStock(Long id, int quantity) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto con ID " + id + " no encontrado"));
        
        product.addStock(quantity);
        return repository.save(product);
    }
    
    /**
     * Reduce el stock de un producto.
     * 
     * @param id el ID del producto
     * @param quantity la cantidad a reducir
     * @return el producto actualizado
     * @throws IllegalArgumentException si el producto no existe o no hay stock suficiente
     */
    public Product decreaseStock(Long id, int quantity) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto con ID " + id + " no encontrado"));
        
        product.reduceStock(quantity);
        return repository.save(product);
    }
    
    /**
     * Obtiene la cantidad total de productos.
     * 
     * @return número de productos
     */
    public long getTotalProducts() {
        return repository.count();
    }
    
    /**
     * Valida que los datos del producto sean correctos.
     * 
     * @param product el producto a validar
     * @throws IllegalArgumentException si algún dato es inválido
     */
    private void validateProduct(Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio debe ser mayor o igual a cero");
        }
        if (product.getStock() == null || product.getStock() < 0) {
            throw new IllegalArgumentException("El stock debe ser mayor o igual a cero");
        }
    }
}
