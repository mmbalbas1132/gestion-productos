package com.gestion.productos.repository;

import com.gestion.productos.model.Product;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Repositorio en memoria para gestionar productos.
 * Utiliza un HashMap para almacenar los productos y simula una base de datos en memoria.
 */
@Log
public class ProductRepository {
    
    private final Map<Long, Product> products;
    private final AtomicLong idGenerator;
    
    public ProductRepository() {
        this.products = new HashMap<>();
        this.idGenerator = new AtomicLong(1);
    }
    
    /**
     * Guarda un nuevo producto o actualiza uno existente.
     * 
     * @param product el producto a guardar
     * @return el producto guardado con su ID asignado
     */
    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(idGenerator.getAndIncrement());
        }
        products.put(product.getId(), product);
        log.info("Producto guardado: " + product.getName() + " (ID: " + product.getId() + ")");
        return product;
    }
    
    /**
     * Busca un producto por su ID.
     * 
     * @param id el ID del producto
     * @return Optional con el producto si existe, Optional.empty() en caso contrario
     */
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }
    
    /**
     * Obtiene todos los productos almacenados.
     * 
     * @return lista de todos los productos
     */
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }
    
    /**
     * Busca productos por categoría.
     * 
     * @param category la categoría a buscar
     * @return lista de productos de la categoría especificada
     */
    public List<Product> findByCategory(String category) {
        if (category == null) {
            return new ArrayList<>();
        }
        return products.values().stream()
                .filter(p -> p.getCategory() != null && p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
    
    /**
     * Busca productos por nombre (búsqueda parcial, sin distinción de mayúsculas).
     * 
     * @param name el nombre o parte del nombre a buscar
     * @return lista de productos que coinciden con el nombre
     */
    public List<Product> findByNameContaining(String name) {
        if (name == null) {
            return new ArrayList<>();
        }
        return products.values().stream()
                .filter(p -> p.getName() != null && 
                        p.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    /**
     * Elimina un producto por su ID.
     * 
     * @param id el ID del producto a eliminar
     * @return true si el producto fue eliminado, false si no existía
     */
    public boolean deleteById(Long id) {
        Product removed = products.remove(id);
        if (removed != null) {
            log.info("Producto eliminado: " + removed.getName() + " (ID: " + id + ")");
            return true;
        }
        return false;
    }
    
    /**
     * Cuenta el número total de productos.
     * 
     * @return número de productos almacenados
     */
    public long count() {
        return products.size();
    }
    
    /**
     * Verifica si existe un producto con el ID especificado.
     * 
     * @param id el ID a verificar
     * @return true si existe, false en caso contrario
     */
    public boolean existsById(Long id) {
        return products.containsKey(id);
    }
    
    /**
     * Elimina todos los productos del repositorio.
     */
    public void deleteAll() {
        products.clear();
        log.info("Todos los productos han sido eliminados");
    }
}
