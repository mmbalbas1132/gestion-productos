package com.gestion.productos.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidad que representa un producto en el sistema.
 * Utiliza Lombok para generar automáticamente getters, setters, 
 * constructores y otros métodos comunes.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private String category;
    
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    /**
     * Verifica si el producto está disponible en stock.
     * 
     * @return true si hay stock disponible, false en caso contrario
     */
    public boolean isAvailable() {
        return stock != null && stock > 0;
    }
    
    /**
     * Reduce el stock del producto en la cantidad especificada.
     * 
     * @param quantity cantidad a reducir
     * @throws IllegalArgumentException si la cantidad es mayor al stock disponible
     */
    public void reduceStock(int quantity) {
        if (stock == null || stock < quantity) {
            throw new IllegalArgumentException("Stock insuficiente");
        }
        this.stock -= quantity;
        this.updatedAt = LocalDateTime.now();
    }
    
    /**
     * Incrementa el stock del producto en la cantidad especificada.
     * 
     * @param quantity cantidad a agregar
     */
    public void addStock(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        }
        this.stock = (this.stock == null ? 0 : this.stock) + quantity;
        this.updatedAt = LocalDateTime.now();
    }
}
